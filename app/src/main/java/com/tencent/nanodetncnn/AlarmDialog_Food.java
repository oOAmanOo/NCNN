package com.tencent.nanodetncnn;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AlarmDialog_Food extends DialogFragment {

//    @Nullable
    public static Dialog dialog1;
    public static String text;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarmdialog_food_layout, container);

        dialog1 = this.getDialog();
        dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager();

        ImageView alarm_food_img = (ImageView) view.findViewById(R.id.alarm_food_img);
        if(text.matches(".*已過期.*")){
            System.out.println(".*已過期.*");
            alarm_food_img.setImageResource(R.drawable.pic1);
        }else if(text.matches(".*即將過期.*")){
            System.out.println(".*即將過期.*");
            alarm_food_img.setImageResource(R.drawable.pic1);
        }else{
            System.out.println("No過期");
            alarm_food_img.setImageResource(R.drawable.pic1);
        }
        System.out.println(text);
        TextView alarm_food_text = (TextView) view.findViewById(R.id.alarm_food_text);
        alarm_food_text.setText(text);
        CheckBox alarm_food_checkBox = (CheckBox) view.findViewById(R.id.alarm_food_checkBox);
        ImageButton alarm_food_close = (ImageButton) view.findViewById(R.id.alarm_food_close);
        alarm_food_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alarm_food_checkBox.isChecked()){
                    Thread alarmUpdate = new Thread(update_alarm);
                    alarmUpdate.start();
                }
                dialog1.dismiss();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }
    private final Runnable update_alarm = new Runnable() {
        public void run() {
            String result;
            try {
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/alarm_update.php?uid="+ MainActivity.uid);//宣告使用post方法連線

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("alarm", "alarm_food"));
                params.add(new BasicNameValuePair("rid", "NULL"));
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
                result = box;
            } catch (Exception e) {
                result = e.toString();
            }
        }
    };

}
