package com.example.day24_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView counter;
    private int count = 0;
    private SharedPreferences preferences;
    private static final String APP_PREFERENCES_COUNT = "APP_PREFERENCES_COUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.counter);
        counter.setText(String.valueOf(count));

        preferences = getSharedPreferences("settings", MODE_PRIVATE);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(APP_PREFERENCES_COUNT, count).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (preferences.contains(APP_PREFERENCES_COUNT)) {
            count = preferences.getInt(APP_PREFERENCES_COUNT, 0);
            counter.setText(String.valueOf(count));
        }
    }

    public void increment(View view) {
        counter.setText(String.valueOf(++count));
    }

    public void reset(View view) {
        count = 0;
        counter.setText(String.valueOf(count));
    }
}
