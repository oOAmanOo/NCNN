package com.tencent.nanodetncnn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivitywelcomeverify extends AppCompatActivity {
    public static String uid;
    public static String to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("data_uid");
        to = bundle.getString("to");
        System.out.println(to);
        if(to.equals("MainActivityverify")){
            Verify_Activity.verify_finish.finish();
        }else if(!to.equals("MainActivity")){
            MainActivity.profilereload_MainActivity.finish();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_activitywelcome);
        if(to.equals("MainActivityverify") || to.equals("MainActivityNotification")){
            handler.sendEmptyMessageDelayed(0,2000);
        }else{
            handler.sendEmptyMessageDelayed(0,500);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(to.equals("Verify")){
                Verify();
            }
            if(to.equals("Alert_History")){
                Alert_History();
            }
            if(to.equals("ToDoList")){
                ToDoList();
            }
            if(to.equals("MainActivity") || to.equals("MainActivityverify") || to.equals("MainActivityNotification")){
                Main();
            }
            super.handleMessage(msg);
        }
    };

    public void Alert_History(){
        Intent intent= new Intent(MainActivitywelcomeverify.this, Alert_History_Activity.class);
        Bundle bundl = new Bundle();
        bundl.putString("data_uid", uid);
        intent.putExtras(bundl);   // put進去
        startActivity(intent);
        finish();
    }
    public void Verify(){
        Intent intent= new Intent(MainActivitywelcomeverify.this, Verify_Activity.class);
        Bundle bundl = new Bundle();
        bundl.putString("data_uid", uid);
        intent.putExtras(bundl);   // put進去
        startActivity(intent);
        finish();
    }
    public void ToDoList(){
        Intent intent= new Intent(MainActivitywelcomeverify.this, ToDoListActivity.class);
        Bundle bundl = new Bundle();
        bundl.putString("data_uid", uid);
        intent.putExtras(bundl);   // put進去
        startActivity(intent);
        finish();
    }
    public void Main(){
        Intent intent= new Intent(MainActivitywelcomeverify.this, MainActivity.class);
        Bundle bundl = new Bundle();
        bundl.putString("data_uid", uid);
        intent.putExtras(bundl);   // put進去
        startActivity(intent);
        finish();
    }
}