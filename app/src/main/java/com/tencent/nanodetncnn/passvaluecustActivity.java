package com.tencent.nanodetncnn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class passvaluecustActivity extends AppCompatActivity {

    String result, alerttime, recipetime, id, data,mid;// 儲存資料用的字串
    String sugar,salt,oil,hate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passvaluecust);
        Bundle bundle = getIntent().getExtras();
        data = bundle.getString("result");
        String[] split = data.split(";");
        id = split[0];
        recipetime = split[2];
        recipetime=recipetime+":"+"00";
        alerttime = split[3];
        alerttime=alerttime+":"+"00";
        mid= "5";
        sugar=split[5];
        salt=split[6];
        oil=split[7];
        hate=split[8];
        //System.out.println(123);

       if(hate.equals("[\"無\"]")){
           hate="[-1]";
       }

        Thread thread = new Thread(mutiThread);
        thread.start(); // 開始執行

        MainActivity.profilereload_MainActivity.finish();
        Intent intent = new Intent(passvaluecustActivity.this, MainActivity.class);
        Bundle bundl = new Bundle();
        bundl.putString("data_uid", id);
        intent.putExtras(bundl);   // put進去
        startActivity(intent);
        finish();
    }

    private Runnable mutiThread = new Runnable() {
        public void run() {
            Looper.prepare();
            // TODO Auto-generated method stub
            HttpClient client = new DefaultHttpClient();

            HttpPost myPost = new HttpPost("http://140.117.71.11/user_modify.php");
            try {
                //System.out.println(14);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("uid",id));
                params.add(new BasicNameValuePair("alertTime",alerttime));
                params.add(new BasicNameValuePair("recipeTime",recipetime));
                params.add(new BasicNameValuePair("mid","5"));
                params.add(new BasicNameValuePair("sugar",sugar));
                params.add(new BasicNameValuePair("salt",salt));
                params.add(new BasicNameValuePair("oil",oil));
                params.add(new BasicNameValuePair("hate",hate));
                myPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                HttpResponse response = new DefaultHttpClient().execute(myPost);
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpResponse httpResponse = httpClient.execute(myPost);//宣告HTTP回應物件
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
//                    updateUserData(result);
                    String return_val = null;
                    try {
                        JSONArray table = null;
                        table = new JSONArray(result);
                        return_val = table.getString(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.mContext2, "個人資料 - "+return_val, Toast.LENGTH_SHORT).show();
                    MainActivity.profilereload_MainActivity.finish();
                    Intent intent = new Intent(passvaluecustActivity.this, MainActivity.class);
                    Bundle bundl = new Bundle();
                    bundl.putString("data_uid", id);
                    intent.putExtras(bundl);   // put進去
                    startActivity(intent);
                    finish();
                }
            });
        }
        private void updateUserData(String result) {
            try {
                JSONArray parent = new JSONArray(result);
                String text =parent.getString(0);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}

