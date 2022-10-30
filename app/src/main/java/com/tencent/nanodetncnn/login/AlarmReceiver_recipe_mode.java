package com.tencent.nanodetncnn.login;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.MainActivitywelcomeverify;
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
import java.util.Random;

public class AlarmReceiver_recipe_mode extends BroadcastReceiver {
    //    建立notificationManager與notification物件
    private NotificationManager notificationManager;
    private Notification notification;

    //    建立能辨識通知差別的ID
    private static int NOTIFICATION_ID = 2;
    private static String NOTIFICATION_id = "2";
    private static String NOTIFICATION_NAME="notify_recipe_mode";
    private final static String NOTIFICATION_description="notify_recipe_mode";

    public static String notify_text;
    public static int threadDone;
    public static String rid;

    private String uid = null;
    public static String multi_result = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        uid = bundle.getString("data_uid");
        try {
            JSONObject obj = null;
            JSONArray table = null;
            JSONObject data = null;
            obj = new JSONObject(multi_result);
            table = obj.getJSONArray("rec_recipe_mode");
            Random random = new Random();
            int value = random.nextInt(table.length() - 1 + 0) + 0;
            data = table.getJSONObject(value);
            notify_text = "為您推薦您可能喜歡的「" + data.getString("name") + "」，點擊以查閱食譜";
            MainActivity.alarmrecipe = data.toString();
            rid = data.getString("rid");
            threadDone = 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Thread alarmUpdate = new Thread(update_alarm);
        alarmUpdate.start();
        Thread thread_upload = new Thread(upload);
        thread_upload.start();

        Intent click_Intent = new Intent(context, MainActivitywelcomeverify.class);
        Bundle bundl = new Bundle();
        bundl.putString("data_uid", uid);
        bundl.putString("to", "MainActivityNotification");
        click_Intent.putExtras(bundl);
        PendingIntent click_pendingIntent = PendingIntent.getActivity(context,0, click_Intent,PendingIntent.FLAG_UPDATE_CURRENT);

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(NOTIFICATION_id, NOTIFICATION_NAME, importance);
        channel.setDescription(NOTIFICATION_description);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

        // 建立通知物件內容
        notification = new Notification.Builder(context)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.bingo_white)
                .setContentTitle("食譜推薦")
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

    private final Runnable update_alarm = new Runnable() {
        public void run() {
            String result;
            try {
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpPost httpPost = new HttpPost("http://140.117.71.11/alarm_update.php?uid="+AlarmActivity.uid);//宣告使用post方法連線

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("alarm", "alarm_recipe_mode"));
                params.add(new BasicNameValuePair("rid", rid));
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
                System.out.println("mode  "+result);
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
                params.add(new BasicNameValuePair("title", "食譜推薦"));
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