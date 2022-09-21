package com.tencent.nanodetncnn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.nanodetncnn.login.LoginActivity;

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

public class MainActivityreg extends AppCompatActivity {


    private EditText usercellphone, username, password;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityreg);

        //
        username = (EditText) findViewById(R.id.username);
        usercellphone = (EditText) findViewById(R.id.usercellphone);
        password = (EditText) findViewById(R.id.password);
        EditText passwordconf = (EditText) findViewById(R.id.passwordconfirm);
        Button btnreg = (Button) findViewById(R.id.btnreg);
        Button btnback = (Button) findViewById(R.id.btnback);


        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().length() != 0 && usercellphone.getText().toString().length() == 10 && password
                        .getText().toString().length() >= 6 && passwordconf.getText().toString().equals(password.getText().toString())) {

                    Thread thread = new Thread(mutiThread);
                    thread.start(); // 開始執
                } else {
                    dialogw();
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityreg.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void dialogw() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("失敗！");  //設置標題
        //builder.setIcon(R.mipmap.ic_launcher_round); //標題前面那個小圖示
        builder.setMessage("請再次確認資料"); //提示訊息
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private void dialogs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityreg.this);

        builder.setTitle("成功");
        //System.out.println(result);//設置標題
        //builder.setIcon(R.mipmap.ic_launcher_round); //標題前面那個小圖示
        //builder.setMessage("帳號註冊成功！"); //提示訊息

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent intent = new Intent(MainActivityreg.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("data_uid", usercellphone.getText().toString());
                intent.putExtras(bundle);   // put進去
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    private Runnable mutiThread = new Runnable() {
        public void run() {
                // TODO Auto-generated method stub
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpPost myPost = new HttpPost("http://140.117.71.11/user_insert.php");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("uid", usercellphone.getText().toString()));
                    params.add(new BasicNameValuePair("name", username.getText().toString()));
                    params.add(new BasicNameValuePair("pw", password.getText().toString()));
                    myPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    HttpResponse httpResponse = client.execute(myPost);//宣告HTTP回應物件
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
            //當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    updateUserData(result);
                }
            });
        }

        private void updateUserData(String result) {
            try {
                JSONArray parent = new JSONArray(result);

                String text =parent.getString(0);
                if(text.equals("註冊成功")){
                    dialogs();
                }else {
                    dialogw();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
