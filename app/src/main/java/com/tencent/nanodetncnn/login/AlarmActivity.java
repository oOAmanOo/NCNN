package com.tencent.nanodetncnn.login;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.tencent.nanodetncnn.MainActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.TimeZone;

public class AlarmActivity extends AppCompatActivity {
    //    提供日、時、分三種時間輸入
    private TextInputEditText edtDay,edtHour,edtMin;
    //    存取目前時間
    public static long currentsystemtime;
    //    存取設定時間
    private long settime;
    //    建立Calendar 物件
    public static Calendar calendar;
    public static Calendar today;
    public static Calendar calendar_food;
    public static Calendar calendar_recipe;
    //    取得日、時、分三種時間輸入
    private String day;
    private String hour;
    private String min;

    public static int threadDone = 0;
    public static String alerttime_food;
    public static String alerttime_recipe;

    public static String uid;
    public static Activity alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("data_uid");
        threadDone = 0;
        Thread thread = new Thread(multiThread);
        thread.start();
        while(threadDone == 0){}

        currentTime(calendar);
        String[] splitstring = alerttime_food.split(":");
        hour = splitstring[0];
        min = splitstring[1];
        setTime(1);
        // 設定alarm
        setAlarm();

        splitstring = alerttime_recipe.split(":");
        hour = splitstring[0];
        min = splitstring[1];
        setTime(2);
        // 設定alarm
        setAlarm2();
        setTime(3);
        // 設定alarm
        setAlarm3();

        //  結束此頁面
        Intent intent=new Intent(AlarmActivity.this, MainActivity.class);
        bundle = new Bundle();
        bundle.putString("data_uid",uid);
        intent.putExtras(bundle);   // put進去
        startActivity(intent);
        finish();
    }

    //            取得目前時間
    private void currentTime(Calendar calendar) {
        // calendar實例化，取得預設時間、預設時區
        calendar = Calendar.getInstance();

        // 設定系統目前時間、目前時區(GMT+8)
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        // 獲得系統目前時間
        currentsystemtime = System.currentTimeMillis();
    }

    //        設定定時
    private void setTime(int i) {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(min));
        calendar.set(Calendar.SECOND, i);
        calendar.set(Calendar.MILLISECOND, 0);

        //獲得定時時間
        settime = calendar.getTimeInMillis();

        //若定時時間(日、時、分)比目前小自動設定為下個月的時間(日、時、分)
        if (currentsystemtime > settime) {
            //增加一個月
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            //重新獲得定時時間
            settime = calendar.getTimeInMillis();
        }
        String text =(calendar.get(Calendar.MONTH)+1)+"月"
                +calendar.get(Calendar.DAY_OF_MONTH)+"日 "
                +calendar.get(Calendar.HOUR_OF_DAY)+":"
                +calendar.get(Calendar.MINUTE);
        System.out.println("text is : "+ text);
    }

    //   設定alarm
    private void setAlarm() {
        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver_food.class);
        Bundle bundl = new Bundle();
        bundl.putString("data_uid", uid);
        intent.putExtras(bundl);   // put進去
        //PendingIntent.getBroadcast調用廣播
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, FLAG_IMMUTABLE);
        //獲得AlarmManager物件
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //設定單次提醒
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }
    private void setAlarm2() {
        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver_recipe_mode.class);
        Bundle bundl = new Bundle();
        bundl.putString("data_uid", uid);
        intent.putExtras(bundl);   // put進去
        //PendingIntent.getBroadcast調用廣播
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, FLAG_IMMUTABLE);
        //獲得AlarmManager物件
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //設定單次提醒
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
    private void setAlarm3() {
        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver_recipe_fridge.class);
        Bundle bundl = new Bundle();
        bundl.putString("data_uid", uid);
        intent.putExtras(bundl);   // put進去
        //PendingIntent.getBroadcast調用廣播
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, FLAG_IMMUTABLE);
        //獲得AlarmManager物件
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //設定單次提醒
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
    private static final Runnable multiThread = new Runnable(){
        public void run()
        {
            String result;
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
                System.out.println(result);
                try {
                    JSONObject obj=null;
                    JSONArray table=null;
                    JSONObject data=null;

                    obj=new JSONObject(result);
                    table=obj.getJSONArray("user_data");
                    data = table.getJSONObject(0);
                    AlarmActivity.alerttime_food = data.getString("alertTime");
                    AlarmActivity.alerttime_recipe = data.getString("recipeTime");
                    AlarmActivity.threadDone = 1;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                result = e.toString();
            }
        }
    };
}
