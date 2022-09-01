package com.example.alarmclock_test;

import static java.lang.Short.valueOf;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeAlarmActivity extends AppCompatActivity {
    //    提供日、時、分三種時間輸入
    private TextInputEditText edtDay,edtHour,edtMin;
    //    存取目前時間
    private long currentsystemtime;
    //    存取設定時間
    private long settime;
    //    建立Calendar 物件
    private Calendar calendar;
    //    取得日、時、分三種時間輸入
    private String day;
    private String hour;
    private String min;
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timealarm);
        Context context = TimeAlarmActivity.this;
        mContext = context;
        Button button;
        button=(Button)findViewById(R.id.btnNotify);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //        使用者輸入情況判斷
                if(isEmptyText()){
                    System.out.println("empty");
                }
                else{
                    // 取得目前時間
                    currentTime();
                    // 設定定時
                    setTime(calendar);
                    // 設定alarm
                    setAlarm();

                    setAlarm2();
                    // 顯示已完成設定的時間
                    showtime();
                    //  結束此頁面
                    finish();

                }
            }
        });

        // Appbar建立標題
        setTitle("時間設定");
        findViews();


    }

    private void findViews() {
        // 取得個元件ID
        edtDay=(TextInputEditText)findViewById(R.id.edtDay);
        edtHour = (TextInputEditText) findViewById(R.id.edtHour);
        edtMin= (TextInputEditText) findViewById(R.id.edtMin);
    }

    //            取得目前時間
    private void currentTime() {
        // calendar實例化，取得預設時間、預設時區
        calendar = Calendar.getInstance();

        // 設定系統目前時間、目前時區(GMT+8)
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        // 獲得系統目前時間
        currentsystemtime=System.currentTimeMillis();
    }

    //        使用者輸入情況判斷
    private boolean isEmptyText(){
        day = edtDay.getText().toString();
        hour = edtHour.getText().toString();
        min = edtMin.getText().toString();

        if(day.isEmpty()|| hour.isEmpty()|| min.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

    //        設定定時
    private void setTime(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(day));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(min));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        //獲得定時時間
        settime = calendar.getTimeInMillis();

        //若定時時間(日、時、分)比目前小自動設定為下個月的時間(日、時、分)
        if (currentsystemtime > settime) {
            //增加一個月
            calendar.add(Calendar.MONTH, 1);
            //重新獲得定時時間
            settime = calendar.getTimeInMillis();
        }
    }

    //   設定alarm
    private void setAlarm() {
        Intent intent = new Intent(TimeAlarmActivity.this, alarmReceiver.class);
        //PendingIntent.getBroadcast調用廣播
        PendingIntent pendingIntent = PendingIntent.getBroadcast(TimeAlarmActivity.this, 0, intent, 0);
        //獲得AlarmManager物件
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //設定單次提醒
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }
    private void setAlarm2() {
        Intent intent2 = new Intent(TimeAlarmActivity.this, alarmReceiver2.class);
        //PendingIntent.getBroadcast調用廣播
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(TimeAlarmActivity.this, 0, intent2, 0);
        //獲得AlarmManager物件
        AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        //設定單次提醒
        alarmManager2.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent2);
    }

    //    顯示已完成設定的時間
    private void showtime() {

//        String text = calendar.get(Calendar.HOUR_OF_DAY) + "點" + calendar.get(Calendar.MINUTE) + "分";

        String text =(calendar.get(Calendar.MONTH)+1)+"月"
                +calendar.get(Calendar.DAY_OF_MONTH)+"日 "
                +calendar.get(Calendar.HOUR_OF_DAY)+":"
                + calendar.get(Calendar.MINUTE);
System.out.println("text is : "+ text);
//        Toast.makeText(this, text,Toast.LENGTH_LONG).show();
//        Toast.makeText(mContext,"設定成功\n"
//                        + "設定時間為\n"+text,Toast.LENGTH_LONG)
//                .show();

    }
//    public void btnNotify(View view) {
//
//        //        使用者輸入情況判斷
//        if(isEmptyText()){
//            System.out.println("emptyyyy");
//            Toast.makeText(TimeAlarmActivity.this,"輸入不正確",Toast.LENGTH_SHORT).show();
//        }
//        else{
//
//            //    取得目前時間
//            currentTime();
//
//            System.out.println("currentTime Done!!");
//            //     設定定時
//            setTime(calendar);
//
//            //   設定alarm
//            setAlarm();
//
//            //    顯示已完成設定的時間
//            showtime();
//
//            //     結束此頁面
////            finish();
//            setContentView(R.layout.activity_main);
//
//        }
//    }






}