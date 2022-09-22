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
import android.widget.LinearLayout;
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

public class AlarmDialog_recipe_mode extends DialogFragment {

//    @Nullable
    public static Dialog dialog1;
    public static String name;
    public static String alarmrecipe;
    public static String alarmrecipe_food;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarmdialog_recipe_mode_layout, container);
        dialog1 = this.getDialog();
        dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager();

        ImageView img = (ImageView) view.findViewById(R.id.alarm_recipe_mode_img);
        img.setImageResource(R.drawable.pic1);
        TextView text = (TextView) view.findViewById(R.id.alarm_recipe_mode_text);
        text.setText(name);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.alarm_recipe_mode_checkBox);
        ImageButton close = (ImageButton) view.findViewById(R.id.alarm_recipe_mode_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    Thread alarmUpdate = new Thread(update_alarm);
                    alarmUpdate.start();
                }
                dialog1.dismiss();
            }
        });

        LinearLayout LinearLayout = (LinearLayout) view.findViewById(R.id.alarm_recipe_mode);
        LinearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    Thread alarmUpdate = new Thread(update_alarm);
                    alarmUpdate.start();
                }
                MainActivity.alarmrecipe = alarmrecipe;
                MainActivity.alarmrecipe_food = alarmrecipe_food;
                MainActivity.scanBtn_public.setVisibility(View.INVISIBLE);
                MergeDetailFragment.currentMode = "alarm";
                MainActivity.replaceFragment(new MergeDetailFragment());
                dialog1.dismiss();
                if(MainActivity.alarm[0] == 1){
                    AlarmDialog_Food.dialog1.dismiss();
                    MainActivity.alarm[0] = 0;
                }
                if(MainActivity.alarm[2] == 1){
                    AlarmDialog_recipe_mode.dialog1.dismiss();
                    MainActivity.alarm[2] = 0;
                }
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
                params.add(new BasicNameValuePair("alarm", "alarm_recipe_mode"));
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
