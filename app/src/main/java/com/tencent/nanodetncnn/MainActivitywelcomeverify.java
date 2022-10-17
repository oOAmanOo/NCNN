package com.tencent.nanodetncnn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.gu.toolargetool.TooLargeTool;

public class MainActivitywelcomeverify extends AppCompatActivity {
    public static String uid;
    public static String to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TooLargeTool.startLogging(getApplication());
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("data_uid");
        to = bundle.getString("to");
        System.out.println(to);
        MainActivity.profilereload_MainActivity.finish();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_activitywelcome);
        handler.sendEmptyMessageDelayed(0,500);

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
            if(to.equals("MainActivity")){
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