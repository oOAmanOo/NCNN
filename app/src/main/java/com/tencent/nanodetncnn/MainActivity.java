package com.tencent.nanodetncnn;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.nanodetncnn.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_main);
        Intent intent=new Intent(MainActivity.this, LoginActivity.class); startActivity(intent);

    }
}



