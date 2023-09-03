package com.example.tredgate_learningapp;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;
import android.view.View;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyNotificationManager {
    private Context context;

    public MyNotificationManager(Context context) {
        this.context = context;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "YourChannel";
            String description = "Channel description";
            int importance = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("YourChannelID", name, importance);
            channel.setDescription(description);
            android.app.NotificationManager notificationManager = context.getSystemService(android.app.NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @SuppressLint("MissingPermission")
    public void sendNotification(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "YourChannelID")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Your title")
                .setContentText("Your message")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        int notificationId = 001;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, builder.build());
    }
}
