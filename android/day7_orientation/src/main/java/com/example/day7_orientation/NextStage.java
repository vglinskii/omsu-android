package com.example.day7_orientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NextStage extends AppCompatActivity {

    private Button orientationButton;
    boolean isLandscape = false;
    private int counter = 0;
    private TextView countText;
    private static final String KEY_COUNTER = "COUNTER";
    private static final String KEY_ORIENTATION = "ORIENTATION";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_stage);

        orientationButton = findViewById(R.id.orientation_button);

        orientationButton.setText(getString(R.string.or_landscape));

        countText = findViewById(R.id.count);

        countText.setText(String.valueOf(counter));

        if(savedInstanceState != null) {
            counter = savedInstanceState.getInt(KEY_COUNTER, 0);
            isLandscape = savedInstanceState.getBoolean(KEY_ORIENTATION, false);
            countText.setText(String.valueOf(counter));
            Toast.makeText(this, "Загружено " + counter + " ворон", Toast.LENGTH_SHORT).show();
            if(isLandscape) {
                orientationButton.setText(getString(R.string.or_portrait));
            } else {
                orientationButton.setText(getString(R.string.or_landscape));
            }
        }
    }

    public void onClick(View view) {
        if(isLandscape) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            orientationButton.setText(getString(R.string.or_landscape));
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            orientationButton.setText(getString(R.string.or_portrait));
        }
        isLandscape = !isLandscape;
    }

    public void incrementCounter(View view) {
        countText.setText(String.valueOf(++counter));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_COUNTER, counter);
        outState.putBoolean(KEY_ORIENTATION, isLandscape);
    }
}