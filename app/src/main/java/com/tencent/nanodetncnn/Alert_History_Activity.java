package com.tencent.nanodetncnn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Alert_History_Activity extends AppCompatActivity {
    ImageView back;
    public static String result_todo;
    public static int count;
    public static String[] alertname = new String[500];
    public static String[] alerttime = new String[500];
    public static String[] alertcontent= new String[500];
    public static RecyclerView recyclerView;
    public static TextView noAlertContent;

    public static String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("data_uid");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_history);
        noAlertContent = findViewById(R.id.noAlertContent);
        back = findViewById(R.id.alert_backstack_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Alert_History_Activity.this, MainActivitywelcomeverify.class);
                Bundle bundl = new Bundle();
                bundl.putString("data_uid", uid);
                bundl.putString("to", "MainActivity");
                intent.putExtras(bundl);   // put進去
                startActivity(intent);
            }
        });
        Thread thread = new Thread(mutiThread);
        thread.start();





    }
    private Runnable mutiThread = new Runnable(){

        String result = null;

        public void run()
        {
            try {
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/bingodb.php?uid="+uid);//宣告使用post方法連線
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
            }catch (Exception e){
                result = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    getlistdata(result);
                }
            });
        }
    };


    public void getlistdata(String result){
        result_todo = result;
        JSONObject obj = null;
        JSONArray list = null;
        JSONObject listdata = null;

        count = 0;

        try{
            obj = new JSONObject(result_todo);
            list = obj.getJSONArray("notify_history");

            if(list.length() == 0){
                noAlertContent.setVisibility(View.VISIBLE);
            }
            else{
                for(int i = 0; i < list.length(); ++i){
                    listdata = list.getJSONObject(i);
                    alertname[count] = listdata.getString("uid");
                    alerttime[count] = listdata.getString("timestamp");
                    alertcontent[count] = listdata.getString("text");
                    ++count;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        throwtoadapter();
    }
    public void throwtoadapter(){
        recyclerView = findViewById(R.id.alert_recylerview);
        AlertHistoryAdapter listAdapter = new AlertHistoryAdapter(getApplicationContext(),alertname,alerttime,alertcontent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);
    }


}