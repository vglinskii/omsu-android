package com.example.day30_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,
                "Принято сообщение:" + intent.getStringExtra("ru.vglinskii.broadcast.Message"),
                Toast.LENGTH_LONG).show();
    }
}
