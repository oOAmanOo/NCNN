package com.tencent.nanodetncnn;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tencent.nanodetncnn.databinding.ActivityMainBinding;
import com.tencent.nanodetncnn.fridge.MyFridgeFragment;
import com.tencent.nanodetncnn.fridge.fridge_editfridge_dfragment2;
import com.tencent.nanodetncnn.fridge.fridge_editfridge_dfragment3;

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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button scanBtn;
    ActivityMainBinding binding;
    public static String result;
    public static int count;
    TextView textView;
    public static ArrayList<String> stringList = new ArrayList<String>();

    private NormalRecipeList normalRecipeList = new NormalRecipeList();

    //    duck dialog
    public static int[] editfridge_count = new int[100];
    public static int[] editfridge_fault = new int[100];
    public static String[] editfridgedb_fid = new String[200];
    public static String[] editfridgedb_did = new String[200];
    public static String[] editfridgedb_name = new String[200];
    public static String[] editfridgedb_position = new String[200];
    public static String[] editfridgedb_insertDate = new String[200];
    public static String[] editfridgedb_expireDate = new String[200];
    public static String[] editfridgedb_uid = new String[200];
    public static int[] editfridgedb_amount = new int[200];
    public static String[] editfridgedb_memo = new String[200];
    public static int[] editfridgedb_editnum = new int[200];
    public static int editfridgedb_index = 0;
    public static String info_editfridge_name;
    public static String info_editfridge_imgName;
    public static String info_editfridgedb_position;
    public static String info_editfridgedb_insertDate;
    public static String info_editfridgedb_expireDate;
    public static String info_editfridgedb_uid;
    public static String info_editfridgedb_amount;
    public static String info_editfridgedb_memo;
    public static int current_editdialog = 0;
    public static int origin_editdialog = 0;
    public static int run_editdialog = 0;
    public static String editjson;
    public static String editjsonupload;
    public static FragmentManager fm_p;
    public static int first_load = 1;
    public static Activity profilereload_MainActivity;

    //notify_user
    public static String[] notify_user_id = new String[200];
    public static String[] notify_user_name = new String[200];
    public static int notify_user_index = 0;
    public static String notify_uid;
    public static String notify_notification;
    public static String notify_notifyTime;
    public static int notify_dialog = 0;

    private static Context mContext;
    public static Context mContext2;

    //bee part
    public static int fridge_run_editdialog = 0;
    public static Button scanBtn_public;

    //John
    public static String uid;
    public static String foodhateid[]=new String[200];
    public static String foodhatename[]=new String[200];
    public static int hatefood_index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        profilereload_MainActivity = this;
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //John
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("data_uid");

        Thread thread = new Thread(mutiThread);
        thread.start();

        final FragmentManager fm = getSupportFragmentManager() ;
        fm_p = fm;
        mContext = MainActivity.this;
        mContext2 = this;



        //fragment傳data的東西
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,new cust1Fragment()).commit();
        //end

        scanBtn =findViewById(R.id.scanBtn);
        scanBtn_public = scanBtn;
        scanBtn.setOnClickListener(this);

        replaceFragment(new MyFridgeFragment());

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.myFridge:
                    replaceFragment(new MyFridgeFragment());
                    scanBtn.setVisibility(View.VISIBLE);
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());
                    scanBtn.setVisibility(View.INVISIBLE);
                    break;
//                case R.id.expiration:
//                    replaceFragment(new ExpirationFragment());
//                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    scanBtn.setVisibility(View.INVISIBLE);
                    break;
            }
            Thread thread_0 = new Thread(mutiThread);
            thread_0.start();
            return true;
        });

    }

    @Override
    public void onClick(View v){
        Intent intent=new Intent(MainActivity.this, Verify_Activity.class); startActivity(intent);
    }

    private void scanCode(){

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() != null){
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scan result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this,"No Result",Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }

    }
    public static void replaceFragment(Fragment fragment){

        Bundle bundle=new Bundle();
        bundle.putString("data",result);

        bundle.putString("data_uid",uid);

//        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm_p.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    // bee part
    public void addFragment(Fragment newF) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction t = fragmentManager.beginTransaction();
        t.replace(R.id.frame_layout, newF);
        t.addToBackStack(null);
        t.commit();
    }

    //        duck dialog
    public static void editdialog_change(int current_editdialog, int origin_editdialog, FragmentManager fm){
        final dialog_fragment dialog_fragment = new dialog_fragment();
        final editfridge_dfragment2 editfridge_dfragment2 = new editfridge_dfragment2();
        final editfridge_dfragment3 editfridge_dfragment3 = new editfridge_dfragment3();
        final editfridge_dfragment4 editfridge_dfragment4 = new editfridge_dfragment4();

        dialog_fragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
        editfridge_dfragment2.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
        editfridge_dfragment3.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
        editfridge_dfragment4.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);

        if(current_editdialog == 0){ //send
            first_load = 1;
            if(MainActivity.notify_dialog == 1){
                Thread thread = new Thread(notifyupload);
                thread.start();
                MainActivity.notify_dialog = 0;
                MainActivity.run_editdialog = 0;
                MainActivity.editfridgedb_index = 0;
            }else{
                if(MainActivity.editjsonupload.equals("[]")){
                    Toast.makeText(mContext, "未編輯任何食物", Toast.LENGTH_SHORT).show();
                }else{
                    Thread thread = new Thread(editupload);
                    thread.start();
                    MainActivity.run_editdialog = 0;
                    MainActivity.editfridgedb_index = 0;
                }
            }
            MainActivity.origin_editdialog = MainActivity.current_editdialog;
            Thread thread_0 = new Thread(mutiThread);
            thread_0.start();
        }else if(current_editdialog == 1){
            fm.beginTransaction().remove(dialog_fragment).commit();
            dialog_fragment.show(fm, "editdialog_tag");
            MainActivity.origin_editdialog = MainActivity.current_editdialog;
        }else if(current_editdialog == 2){
            if(MainActivity.run_editdialog == 0){
                Thread thread = new Thread(editsearch);
                thread.start();
                MainActivity.run_editdialog = 1;
            }else if(MainActivity.run_editdialog == 1){
                fm.beginTransaction().remove(editfridge_dfragment2).commit();
                editfridge_dfragment2.show(fm, "editdialog_tag");
                MainActivity.run_editdialog = 0;
                MainActivity.origin_editdialog = MainActivity.current_editdialog;
            }
        }else if(current_editdialog == 3){
            fm.beginTransaction().remove(editfridge_dfragment3).commit();
            editfridge_dfragment3.show(fm, "editdialog_tag");
            MainActivity.current_editdialog = MainActivity.origin_editdialog;
        }else if(current_editdialog == 4){
            fm.beginTransaction().remove(editfridge_dfragment4).commit();
            editfridge_dfragment4.show(fm, "editdialog_tag");
            MainActivity.current_editdialog = MainActivity.origin_editdialog;
        }else if(current_editdialog == -1){
            MainActivity.run_editdialog = 0;
            MainActivity.editfridgedb_index = 0;
            Thread thread_0 = new Thread(mutiThread);
            thread_0.start();
//            MainActivity.recipe_reload = 1;
//            dialog_fragment.listDid.length = 0;
        }
    }

    // duck dialog
    public static void editfridgedialog_change(int current_editdialog, int origin_editdialog, FragmentManager fm){
        final fridge_editfridge_dfragment2 editfridge_dfragment2 = new fridge_editfridge_dfragment2();
        final fridge_editfridge_dfragment3 editfridge_dfragment3 = new fridge_editfridge_dfragment3();

        editfridge_dfragment2.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
        editfridge_dfragment3.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);

        if(current_editdialog == 0){ //send
            first_load = 1;
            if(MainActivity.notify_dialog == 1){
                Thread thread = new Thread(notifyupload);
                thread.start();
                MainActivity.notify_dialog = 0;
                MainActivity.run_editdialog = 0;
                MainActivity.editfridgedb_index = 0;
            }else{
                if(MainActivity.editjsonupload.equals("[]")){
                    Toast.makeText(mContext, "未編輯任何食物", Toast.LENGTH_SHORT).show();
                }else{
                    Thread thread = new Thread(editupload);
                    thread.start();
                    MainActivity.run_editdialog = 0;
                    MainActivity.editfridgedb_index = 0;
                }
            }
            Thread thread_0 = new Thread(mutiThread);
            thread_0.start();
        }else if(current_editdialog == 2){
            if(MainActivity.fridge_run_editdialog == 0){
                Thread thread = new Thread(editsearch);
                thread.start();
                MainActivity.fridge_run_editdialog = 1;
            }else if(MainActivity.fridge_run_editdialog == 1){
                fm.beginTransaction().remove(editfridge_dfragment2).commit();
                editfridge_dfragment2.show(fm, "editdialog_tag");
                MainActivity.fridge_run_editdialog = 0;
            }
        }else if(current_editdialog == 3){
            fm.beginTransaction().remove(editfridge_dfragment3).commit();
            editfridge_dfragment3.show(fm, "editdialog_tag");
        }else if(current_editdialog == -1){
            MainActivity.run_editdialog = 0;
            MainActivity.editfridgedb_index = 0;
            Thread thread_0 = new Thread(mutiThread);
            thread_0.start();
        }
    }

    public static Handler UIHandler;
    static
    {
        UIHandler = new Handler(Looper.getMainLooper());
    }
    public static void runOnUI(Runnable runnable) {
        UIHandler.post(runnable);
    }


    private static final Runnable mutiThread = new Runnable(){
        public void run()
        {
            try {
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/bingodb.php?=duck");//宣告使用post方法連線
                HttpResponse httpResponse = httpClient.execute(httpPost);//宣告HTTP回應物件
                HttpEntity httpEntity = httpResponse.getEntity();//宣告HTTP實體化物件
                InputStream inputStream = httpEntity.getContent();//宣告輸入串流
                //讀取輸入船劉並存到字串
                //取得資料後可在此處理
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"),8);
                String  box = "";
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    box += line ;
                    box += "\n";
                }
                inputStream.close();
                result = box;

                //John
                ProfileFragment.user(result,uid);
                EditFragment.user(result,uid);
                cust1Fragment.user(uid);
                cust22222Fragment.hate(result,uid);
                cust3Fragment.user(result,uid);
                try {
                    JSONObject obj=null;
                    JSONArray table=null;
                    JSONObject data_json=null;

                    obj=new JSONObject(result);
                    table=obj.getJSONArray("food_hate");
                    for (int i = 0; i < table.length() ; i++) {
                        data_json=table.getJSONObject(i);
                        foodhateid[i]=data_json.getString("did");
                        foodhatename[i]=data_json.getString("name");
                        ++hatefood_index;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //recipe
                AllRecipeList.result(result);
                AllRecipeList.getallfoodhistory(result);
                NormalRecipeList.recipe(result);
                NormalRecipeList.getallfoodhistory(result);
                ManageRecipeList.recipe(result);
                ManageRecipeList.getallfoodhistory(result);
                FitnessRecipeList.recipe(result);
                FitnessRecipeList.getallfoodhistory(result);
                RelaxRecipeList.recipe(result);
                RelaxRecipeList.getallfoodhistory(result);
                AutoRecipeList.recipe(result);
                AutoRecipeList.getallfoodhistory(result);

                // bee part
                MyApplication.foodModelPraise.praiseFoodJsonString(result);

                //notify_user
                try {
                    JSONObject obj = null;
                    JSONArray table = null;
                    JSONObject data = null;
                    obj = new JSONObject(result);
                    //table : food_dic , fridge , fridge_history , mode , notify_history , recipe , recipe_food , user , user_hate , user_notify
                    table = obj.getJSONArray("user");
                    //data list
                    notify_user_index = 0;
                    for (int i = 0; i < table.length(); i++) {
                        data = table.getJSONObject(i);
                        //data data
                        notify_user_id[i] = data.getString("uid");
                        notify_user_name[i] = data.getString("name");
                        ++notify_user_index;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                result = e.toString();
            }
            // 當這個執行緒完全跑完後執行
            MainActivity.runOnUI(new Runnable() {
                public void run() {
                    if(first_load == 1){
                        replaceFragment(new MyFridgeFragment());
                        first_load = 0;
                    }
                }
            });
        }
    };

    //duck dialog
    private static final Runnable editsearch = new Runnable() {
        public void run() {
            String result_data;
            try {
//                String url = "http://140.117.71.11/bingodb_copy.php?uid="+user;
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/fridge_editsearch.php?uid=duck");//宣告使用post方法連線
                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("uid",usernameEditText.getText().toString()));
                params.add(new BasicNameValuePair("json", MainActivity.editjson));
                params.add(new BasicNameValuePair("uid", "duck"));
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
                result_data = box;
//                System.out.println("result_data: "+result_data);
            } catch (Exception e) {
                result_data = e.toString();
            }

            String finalResult = result_data;
            MainActivity.runOnUI(new Runnable() {
                public void run() {
                    try {
                        JSONArray table = null;
                        JSONArray tablea = null;
                        JSONObject data = null;
                        table =new JSONArray(finalResult);

                        editfridgedb_index = 0;
                        for (int j = 0; j < table.length(); j++) {
                            //table : food_dic , fridge , fridge_history , mode , notify_history , recipe , recipe_food , user , user_hate , user_notify
                            tablea = table.getJSONArray(j);
                            //data list
                            editfridge_count[j] = 0;
                            for (int i = 0; i < tablea.length(); i++) {
                                data = tablea.getJSONObject(i);
                                editfridgedb_fid[editfridgedb_index] = data.getString("fid");
                                editfridgedb_did[editfridgedb_index] = data.getString("did");
                                editfridgedb_name[editfridgedb_index] = data.getString("name");
                                editfridgedb_position[editfridgedb_index] = data.getString("position");
                                editfridgedb_insertDate[editfridgedb_index] = data.getString("insertDate");
                                editfridgedb_expireDate[editfridgedb_index] = data.getString("expireDate");
                                editfridgedb_uid[editfridgedb_index] = data.getString("uid");
                                editfridgedb_amount[editfridgedb_index] = Integer.parseInt(data.getString("amount"));
                                if(data.getString("memo").equals("")){
                                    editfridgedb_memo[editfridgedb_index] = "#";
                                }else{
                                    editfridgedb_memo[editfridgedb_index] = data.getString("memo");
                                }
                                editfridgedb_editnum[editfridgedb_index] = 0;
                                ++editfridge_count[j];
                                ++editfridgedb_index;
                            }
                            editfridge_count[j+1] = 0;
                        }
                        if(MainActivity.run_editdialog == 1){
                            MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, MainActivity.fm_p);
                        }else if(MainActivity.fridge_run_editdialog == 1){
                            MainActivity.editfridgedialog_change(2, 0, MainActivity.fm_p);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private static final Runnable editupload = new Runnable() {
        public void run() {
            String result_data;
            try {
//                String url = "http://140.117.71.11/bingodb_copy.php?uid="+user;
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/fridge_modify.php?uid=duck");//宣告使用post方法連線

                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("uid",usernameEditText.getText().toString()));
//                System.out.println(MainActivity.editjsonupload);
                params.add(new BasicNameValuePair("json", MainActivity.editjsonupload));
                params.add(new BasicNameValuePair("uid", "duck"));
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
                result_data = box;
//                System.out.println(result_data);
            } catch (Exception e) {
                result_data = e.toString();
            }

            String finalResult = result_data;
            MainActivity.runOnUI(new Runnable() {
                public void run() {
                    String return_val = null;
                    try {
                        JSONArray table = null;
                        table = new JSONArray(finalResult);
                        return_val = table.getString(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(mContext, "冰箱存儲 - "+return_val, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private static final Runnable notifyupload = new Runnable() {
        public void run() {
            String result_data;
            try {
//                String url = "http://140.117.71.11/bingodb_copy.php?uid="+user;
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/notify_insert.php?uid=duck");//宣告使用post方法連線

                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("uid",usernameEditText.getText().toString()));
                params.add(new BasicNameValuePair("uid", MainActivity.notify_uid));
                params.add(new BasicNameValuePair("notification", MainActivity.notify_notification));
                params.add(new BasicNameValuePair("notifyTime", MainActivity.notify_notifyTime));
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
                result_data = box;
//                System.out.println(result_data);
            } catch (Exception e) {
                result_data = e.toString();
            }

            String finalResult = result_data;
            MainActivity.runOnUI(new Runnable() {
                public void run() {
                    String return_val = null;
                    try {
                        JSONArray table = null;
                        table = new JSONArray(finalResult);
                        return_val = table.getString(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(mContext, "待辦清單 - "+return_val, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}