package com.tencent.nanodetncnn.login;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.tencent.nanodetncnn.R;

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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AlarmReceiver_food extends BroadcastReceiver {
    //    建立notificationManager與notification物件
    private NotificationManager notificationManager;
    private Notification notification;

    //    建立能辨識通知差別的ID
    private static int NOTIFICATION_ID = 1;
    private static String NOTIFICATION_id = "1";
    private static String NOTIFICATION_NAME="notify_food";
    private final static String NOTIFICATION_description="notify_food";

    public static String notify_text;
    public static int threadDone;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("receive_food");
        Thread thread = new Thread(multiThread);
        thread.start();
        while(threadDone == 0){}
        Thread alarmUpdate = new Thread(update_alarm);
        alarmUpdate.start();
        Thread thread_upload = new Thread(upload);
        thread_upload.start();
        Intent click_Intent = new Intent(context, LoginActivity.class);
        PendingIntent click_pendingIntent = PendingIntent.getActivity(context,0, click_Intent,0);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(NOTIFICATION_id, NOTIFICATION_NAME, importance);
        channel.setDescription(NOTIFICATION_description);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

        // 建立通知物件內容
        notification = new Notification.Builder(context)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("即期過期提醒")
                .setContentText(notify_text)
                .setContentIntent(click_pendingIntent)
                .setVibrate(new long[]{0,100,200,300,400,500})
                .setChannelId(NOTIFICATION_id)
                .build();

        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        notification.flags = Notification.DEFAULT_VIBRATE;
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        // 發送通知
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private static final Runnable multiThread = new Runnable(){
        public void run()
        {
            String result;
            try {
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/bingodb.php?uid="+ AlarmActivity.uid);//宣告使用post方法連線
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

                try {
                    JSONObject obj = null;
                    JSONArray table = null;
                    JSONObject data = null;
                    notify_text = "冰箱存儲有";
                    int alert = 1;
                    int expire = 1;
                    obj=new JSONObject(result);

                    table=obj.getJSONArray("alert_food");
                    if(table.length() == 0){
                        alert = 0;
                    }else{
                        notify_text += table.length() + "樣即將過期，";
                    }

                    table=obj.getJSONArray("expire_food");
                    if(table.length() == 0){
                        expire = 0;
                    }else{
                        notify_text += table.length() + "樣已過期，";
                    }

                    if(alert == 0 && expire == 0){
                        notify_text = "美好的一天~ 冰箱沒有任何即期過期食品";
                    }else if(alert == 0 && expire != 0){
                        notify_text += "請盡快清除";
                    }else if(alert != 0 && expire != 0){
                        notify_text += "請盡速處理";
                    }else{
                        notify_text += "請盡快清除";
                    }
                    threadDone = 1;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                result = e.toString();
            }
        }
    };
    private final Runnable update_alarm = new Runnable() {
        public void run() {
            String result;
            try {
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/alarm_update.php?uid="+AlarmActivity.uid);//宣告使用post方法連線

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("alarm", "alarm_food"));
                params.add(new BasicNameValuePair("alarm", "alarm_food"));
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
    private static final Runnable upload = new Runnable() {
        public void run() {
            String result;
            try {
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/notify_history_insert.php?uid="+AlarmActivity.uid);//宣告使用post方法連線

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("title", "即期過期提醒"));
                params.add(new BasicNameValuePair("text", notify_text));
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