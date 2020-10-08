package com.example.day30_broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String GIT_PUSH_ACTION = "ru.vglinskii.action.GIT_PUSH";
    private static final String BRANCH_CHANGES = "Added new data!";
    private TextView time;
    private TimeTickBroadcastReceiver timeTickBroadcastReceiver = new TimeTickBroadcastReceiver();
    private MessageReceiver messageReceiver = new MessageReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        onTimeReceive();
        this.registerReceiver(messageReceiver, new IntentFilter(GIT_PUSH_ACTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(messageReceiver);
    }

    public void sendBroadcastMessage(View view) {
        Intent intent = new Intent();
        intent.setAction(GIT_PUSH_ACTION);
        intent.putExtra("ru.vglinskii.broadcast.Message", BRANCH_CHANGES);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

    public void registerTimeTick(View view) {
        this.registerReceiver(timeTickBroadcastReceiver,
                new IntentFilter("android.intent.action.TIME_TICK"));
        Toast.makeText(getApplicationContext(), "Receiver ON!", Toast.LENGTH_LONG).show();
    }

    public void unregisterTimeTick(View view) {
        this.unregisterReceiver(timeTickBroadcastReceiver);
        Toast.makeText(getApplicationContext(), "Receiver OFF!", Toast.LENGTH_LONG).show();
    }

    public void onTimeReceive() {
        Intent intent = getIntent();
        if(intent != null && intent.getStringExtra("time") != null) {
            time.setText(intent.getStringExtra("time"));
        }
    }
}
