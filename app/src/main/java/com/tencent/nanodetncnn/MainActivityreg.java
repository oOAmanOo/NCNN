package com.tencent.nanodetncnn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.nanodetncnn.login.LoginActivity;

public class MainActivityreg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityreg);

        //
        EditText username=(EditText) findViewById(R.id.username);
        EditText useremail=(EditText) findViewById(R.id.useremail);
        EditText password=(EditText) findViewById(R.id.password);
        EditText passwordconf=(EditText) findViewById(R.id.passwordconfirm);
        Button btnreg=(Button) findViewById(R.id.btnreg);
        Button btnback=(Button) findViewById(R.id.btnback);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().length()!=0&&useremail.getText().toString().length()!=0&&password
                        .getText().toString().length()>=6&&passwordconf.getText().toString().equals(password.getText().toString())){
                    //資料新增至資料庫
                    dialogs();
                }else{
                    dialogw();
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivityreg.this, LoginActivity.class); startActivity(intent);
            }
        });


    }

    private void dialogw() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("錯誤！");  //設置標題
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("成功了啦");  //設置標題
        //builder.setIcon(R.mipmap.ic_launcher_round); //標題前面那個小圖示
        builder.setMessage("帳號註冊成功！"); //提示訊息

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent intent=new Intent(MainActivityreg.this, MainActivity.class); startActivity(intent);

            }
        });
        builder.create().show();
    }
}