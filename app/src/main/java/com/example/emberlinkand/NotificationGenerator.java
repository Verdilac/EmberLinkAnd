package com.example.emberlinkand;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationGenerator {
    private Context mContext;
    private String mChannelId;
    private int mNotificationId = 1;

    public NotificationGenerator(Context context) {
        mContext = context;
        mChannelId = mContext.getString(R.string.my_channel);

        // Create a notification channel
        createNotificationChannel();
    }

    public void sendNotification(String title, String text, int iconResourceId) {
        // Build the notification that we will be sending
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, mChannelId)
                .setSmallIcon(iconResourceId)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(mContext);

        // Permission checks
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mContext.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Permission not granted, return
                return;
            }
        }

        // Send notification
        notificationManagerCompat.notify(mNotificationId++, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = mContext.getString(R.string.channel_name);
            String description = mContext.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(mChannelId, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}