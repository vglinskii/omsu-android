package com.example.day30_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeTickBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder msgStr = new StringBuilder("Current time: ");
        Format formatter = new SimpleDateFormat("hh:mm a", Locale.US);
        msgStr.append(formatter.format(new Date()));
        Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
        Intent changeTimeIntent = new Intent("ru.vglinskii.action.CHANGE_TIME");
        changeTimeIntent.putExtra("time", msgStr.toString());
        changeTimeIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        context.startActivity(changeTimeIntent);
    }
}
