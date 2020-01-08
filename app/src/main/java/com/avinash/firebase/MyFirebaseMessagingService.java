package com.avinash.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        Log.d("TOKEN", token);

        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0) {
            createNotification(remoteMessage.getData());
        }
    }

    public void createNotification(Map<String, String> notificationData) {
//        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher);
//        style.bigPicture(icon);

        NotificationManager notificationManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "DemoId";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_DEFAULT);

            //Configure Notification Channel
            notificationChannel.setDescription("My Notification");
            notificationChannel.enableLights(true);


            notificationManager.createNotificationChannel(notificationChannel);
        }
        Context context = this;
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle( notificationData.get("title"))
                .setAutoCancel(true)
                .setContentText(Html.fromHtml(notificationData.get("message")))
//                .setStyle(style)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.mipmap.ic_launcher))
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setPriority( Notification.PRIORITY_MAX);


        notificationManager.notify(1, notificationBuilder.build());

    }
}
