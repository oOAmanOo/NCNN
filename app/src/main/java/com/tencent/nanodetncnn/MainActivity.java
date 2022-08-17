// Tencent is pleased to support the open source community by making ncnn available.
//
// Copyright (C) 2021 THL A29 Limited, a Tencent company. All rights reserved.
//
// Licensed under the BSD 3-Clause License (the "License"); you may not use this file except
// in compliance with the License. You may obtain a copy of the License at
//
// https://opensource.org/licenses/BSD-3-Clause
//
// Unless required by applicable law or agreed to in writing, software distributed
// under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
// CONDITIONS OF ANY KIND, either express or implied. See the License for the
// specific language governing permissions and limitations under the License.

package com.tencent.nanodetncnn;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MainActivity extends FragmentActivity implements SurfaceHolder.Callback
{
    public static final int REQUEST_CAMERA = 100;

    private NcnnYolov5 ncnnyolov5 = new NcnnYolov5();
    final fragment1 fragment1_test = new fragment1();

    private int facing = 0;

    private Spinner spinnerModel;
    private Spinner spinnerCPUGPU;
    private int current_model = 0;
    private int current_cpugpu = 0;
    public static char result_java = '1';
    private int duplicate_class = 0;
    final Context context = this;
    JSONObject obj = null;
    JSONArray table = null;
    JSONObject data = null;
    public static String uid;
    public static String confirm_class_list[];
    public static String confirm_class_name[];
    public static String class_list_checked[];
    public static String fridge_did[];
    public static String fridge_name[];
    public static String fridge_position[];
    public static String fridge_expiredate[];
    public static String fridge_imgName[];
    public static String fridge_amount[];
    public static String fridge_memo[];
    int check = 0;

    private SurfaceView cameraView;
    private String result;


    public MainActivity() throws IOException{

    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        final FragmentManager fm = getSupportFragmentManager() ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        Thread thread = new Thread(multiThread);
        thread.start();

        cameraView = (SurfaceView) findViewById(R.id.cameraview);

        cameraView.getHolder().setFormat(PixelFormat.RGBA_8888);
        cameraView.getHolder().addCallback(this);

        Button buttonSwitchCamera = (Button) findViewById(R.id.buttonSwitchCamera);
        buttonSwitchCamera.setOnClickListener(arg0 -> {

            int new_facing = 1 - facing;

            ncnnyolov5.closeCamera();

            ncnnyolov5.openCamera(new_facing);

            facing = new_facing;
        });

        spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
            {
                if (position != current_model)
                {
                    current_model = position;
                    reload();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });

        spinnerCPUGPU = (Spinner) findViewById(R.id.spinnerCPUGPU);
        spinnerCPUGPU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
            {
                if (position != current_cpugpu)
                {
                    current_cpugpu = position;
                    reload();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });

        Button VarButton = (Button) findViewById(R.id.endVarify);
        VarButton.setOnClickListener(view -> {
            if(result_java == '2'){
                result_java = '1';
                NcnnYolov5.varifyCheck(result_java);
            }else {
                result_java = '2';
                NcnnYolov5.varifyCheck(result_java);
//            Toast toast = Toast.makeText( MainActivity.this, "點了按鈕"+result_java, Toast.LENGTH_SHORT);
//            toast.show();

                try {
                    FileInputStream fis = new FileInputStream("/data/data/com.tencent.nanodetncnn/result.txt");
                    BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
//              BufferedReader in = new BufferedReader(new FileReader("result.txt"));
                    Scanner read = new Scanner(in);
                    read.useDelimiter("\n");
                    String title, category, runningTime, year, price;
                    ArrayList<String> class_list = new ArrayList<String>(500);

                    while (read.hasNext()) {
                        String next_class;
                        next_class = read.next();
                        class_list.forEach((e) -> {
//                      System.out.println(e);
//                      System.out.println(next_class);
                            if (e.equals(next_class)) {
                                duplicate_class = 1;
                            }
//                      System.out.println(duplicate_class);
                        });
                        if (duplicate_class == 0) {
                            class_list.add(next_class);
                        }
                        duplicate_class = 0;
                    }


//        ********************************************************************************************

                    confirm_class_list = class_list.toArray(new String[class_list.size()]);
                    class_list_checked = class_list.toArray(new String[class_list.size()]);

                    check = confirm_class_list.length;
                    try {
                        obj = new JSONObject(result);
                        table = obj.getJSONArray("food_dic");
                        for (int i = 0; i < table.length(); i++) {
                            data = table.getJSONObject(i);
                            for (int j = 0; j < confirm_class_list.length; j++) {
                                if (data.getString("engName").equals(confirm_class_list[j])) {
                                    confirm_class_list[j] = data.getString("name");
                                    --check;
                                    break;
                                }
                            }
                            if (check == 0) {
                                break;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //delete after db built complete
                    check = 0;
                    fragment1_test.show(fm, "dialog_tag");
//        ********************************************************************************************

//                    Toast toast = Toast.makeText(MainActivity.this, "-" + class_list + "-", Toast.LENGTH_SHORT);
//                    toast.show();
//                      class_list.forEach(t -> System.out.println(t));
                } catch (Exception ex) {}
            }
        });
        reload();
    }

    private void reload()
    {
        boolean ret_init = ncnnyolov5.loadModel(getAssets(), current_model, current_cpugpu);
        if (!ret_init)
        {
            Log.e("MainActivity", "ncnnyolv5 loadModel failed");
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        ncnnyolov5.setOutputWindow(holder.getSurface());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }

        ncnnyolov5.openCamera(facing);
    }

    @Override
    public void onPause()
    {
        super.onPause();

        ncnnyolov5.closeCamera();
    }



    //    **************************************** load database_info ****************************************************************

    private final Runnable multiThread = new Runnable() {
        public void run() {
            try {
//                String url = "http://140.117.71.11/bingodb_copy.php?uid="+user;
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/bingodb_copy.php?uid=duck");//宣告使用post方法連線
//                HttpPost httpPost = new HttpPost("http://140.117.71.11/user_insert.php");//宣告使用post方法連線

                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("uid",usernameEditText.getText().toString()));
                params.add(new BasicNameValuePair("uid","duck"));
                httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                HttpResponse httpResponse = httpClient.execute(httpPost);//宣告HTTP回應物件
                HttpEntity httpEntity = httpResponse.getEntity();//宣告HTTP實體化物件
                InputStream inputStream = httpEntity.getContent();//宣告輸入串流

                //讀取輸入船劉並存到字串
                //取得資料後可在此處理
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                String box = "";
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    box += line;
                    box += "\n";
                }
                inputStream.close();
                result = box;
            } catch (Exception e) {
                result = e.toString();
            }


            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {

                    String a = result;


//                    try {
//                        table = new JSONArray(result);
////                        System.out.println(table.getString(0));
//                        a = table.getString(0);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
////                        System.out.println(table);
////                    result="a";
//                    if(a.equals("註冊")){
//                        System.out.println("same");
//                    }else{
//                        System.out.println("not the same");
//                    }
//                    System.out.println(a);
//                    System.out.println(result);

//                    try {
//                            obj = new JSONObject(result);
//
////                        System.out.println(obj);
//                            //table : food_dic , fridge , fridge_history , mode , notify_history , recipe , recipe_food , user , user_hate , user_notify
//                            table = obj.getJSONArray("food_dic");
////                        System.out.println(table);
//                       //data list
//                        for (int i = 0; i < table.length(); i++) {
//                            data = table.getJSONObject(i);
////                            System.out.println(data);
//                            //data data
////                            System.out.println(data.getString("name"));
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
            });
        }
    };

//    ********************************************************************************************************

//    private void replaceFragment(Fragment fragment){
//
//        FragmentManager fragmentManager = getFragmentManager();
//        Bundle bundle=new Bundle();
////        bundle.putString("data",scount);
////        FragmentManager fragmentManager = getSupportFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragment.setArguments(bundle);
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.MainActivity, fragment);
//        fragmentTransaction.commit();
//    }

}