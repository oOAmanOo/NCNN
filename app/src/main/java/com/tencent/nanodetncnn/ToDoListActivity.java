package com.tencent.nanodetncnn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.databinding.ActivityToDoListBinding;

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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity {
    ActivityToDoListBinding binding;
    ImageView back;
    public static String result_todo;
    public static String temp_result;
    public static String[] listnid = new String[500];
    public static String[] listname = new String[500];
    public static String[] listtime = new String[500];
    public static String[] listfood = new String[500];
    public static String[] listdone = new String[500];

    public static int count;
    public static int delete_position;

    public static RecyclerView recyclerView;
    public static Activity todolist;
    public static TextView noTodoContent;

    public static String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("data_uid");
        todolist = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        noTodoContent = findViewById(R.id.noTodoContent);
        back = findViewById(R.id.list_backstack_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToDoListActivity.this,MainActivitywelcomeverify.class);
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
                temp_result = result;

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
            list = obj.getJSONArray("user_notify");

//            for(int i = 0; i < list.length(); ++i){
//                listdata = list.getJSONObject(i);
//                listdone[i] = listdata.getString("done");
//            }

            for (int i = 0; i < list.length(); i++) {
                listdata = list.getJSONObject(i);

                if(listdata.getString("done").equals("0")){
                    noTodoContent.setVisibility(View.INVISIBLE);
                    listnid[count] = listdata.getString("nid");
                    listname[count] = listdata.getString("uid");
                    listtime[count] = listdata.getString("notifyTime");
                    listfood[count] = listdata.getString("notification");
                    ++count;
                }
                else{
                    noTodoContent.setVisibility(View.VISIBLE);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        throwtoadapter();

    }
    public void throwtoadapter(){
        recyclerView = findViewById(R.id.todolist_recylerview);

        ToDoListAdapter listAdapter = new ToDoListAdapter(getApplicationContext(),listname,listfood,listtime);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(listAdapter);

        listAdapter.setOnItemClickedListener(new ToDoListAdapter.OnItemClickedListener() {
            @Override
            public void onItemDelete(int position) {
                delete_position = position;
                listdone[position] = "1";
                Thread thread = new Thread(editupload);
                thread.start();

            }
        });



    }
    private final Runnable editupload = new Runnable() {
        public void run() {
            String result_data;
            try {
//                String url = "http://140.117.71.11/bingodb_copy.php?uid="+user;
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/notify_modify.php?uid=" + uid);//宣告使用post方法連線

                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("uid",usernameEditText.getText().toString()));
                params.add(new BasicNameValuePair("nid", listnid[delete_position]));
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
            } catch (Exception e) {
                result_data = e.toString();
            }

            String finalResult = result_data;
            MainActivity.runOnUI(new Runnable() {
                public void run() {
                    ToDoListActivity.todolist.finish();
                    Intent intent = new Intent(ToDoListActivity.this,ToDoListActivity.class);
                    Bundle bundl = new Bundle();
                    bundl.putString("data_uid", uid);
                    intent.putExtras(bundl);   // put進去
                    overridePendingTransition(0,0);
                    startActivity(intent);
                    overridePendingTransition(0,0);

                }
            });
        }
    };




}