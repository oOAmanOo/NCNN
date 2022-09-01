package com.example.alarmclock_test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class alarmReceiver extends BroadcastReceiver {
    //    建立notificationManager與notification物件
    private NotificationManager notificationManager;
    private Notification notification;

    //    建立能辨識通知差別的ID
    private static int NOTIFICATION_ID = 1;
    private static String NOTIFICATION_id = "1";
    private static String NOTIFICATION_NAME="notify";
    private final static String NOTIFICATION_description="notify";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent click_Intent = new Intent(context,TimeAlarmActivity.class);
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
                .setContentTitle("訊息")
                .setContentText("要量血壓囉!")
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
}