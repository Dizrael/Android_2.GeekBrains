package ru.dizraelapps.msgtodscts.broadcast;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import ru.dizraelapps.msgtodscts.R;

public class MessageReceiver extends BroadcastReceiver {

    private final static String NAME_MSG = "MSG";
    private static final String TAG = "MessageBroadcastReceiver";
    private int messageId = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(NAME_MSG);
        if (message == null){
            message = "";
        }
        Log.d(TAG, message);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "2")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Broadcast Receiver")
                .setContentText(message);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());

    }
}
