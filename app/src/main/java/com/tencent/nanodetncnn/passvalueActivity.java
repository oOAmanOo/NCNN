package com.tencent.nanodetncnn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;
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

public class passvalueActivity extends AppCompatActivity {

    String result, alerttime, recipetime, id, data,mid,sso,veg;// 儲存資料用的字串
    String sugar,salt,oil;
    TextView f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_passvalue);
        f=(TextView) findViewById(R.id.f);
        Bundle bundle = getIntent().getExtras();
        data = bundle.getString("result");
        String[] split = data.split(";");
        id = split[0];
        recipetime = split[2];
        recipetime=recipetime+":"+"00";
        alerttime = split[3];
        alerttime=alerttime+":"+"00";
        mid= split[4];

        if (mid.equals("一般")){
            mid="1";
        }
        if (mid.equals("健身")){
            mid="2";
        }
        if (mid.equals("頹廢")){
            mid="3";
        }
        if (mid.equals("管理")){
            mid="4";
        }
        if (mid.equals("客製化")){
            mid="5";
        }

        Thread thread = new Thread(mutiThread);
        thread.start(); // 開始執行


    }

    private Runnable mutiThread = new Runnable() {
        public void run() {
            Looper.prepare();
            // TODO Auto-generated method stub
            HttpClient client = new DefaultHttpClient();

            HttpPost myPost = new HttpPost("http://140.117.71.11/user_modify.php");
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("uid",id));
                params.add(new BasicNameValuePair("alertTime",alerttime));
                params.add(new BasicNameValuePair("recipeTime",recipetime));
                params.add(new BasicNameValuePair("mid",mid));
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
                    String return_val = null;
                    try {
                        JSONArray table = null;
                        table = new JSONArray(result);
                        return_val = table.getString(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.mContext2, "個人資料 - "+return_val, Toast.LENGTH_SHORT).show();
//                    updateUserData(result);
                    MainActivity.profilereload_MainActivity.finish();
                    Intent intent = new Intent(passvalueActivity.this, MainActivity.class);
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