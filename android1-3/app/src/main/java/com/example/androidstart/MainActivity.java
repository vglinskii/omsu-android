package com.example.androidstart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mHelloTextView;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloTextView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        Button buttonCounter = findViewById(R.id.button_counter);

        button.setOnClickListener((view) -> {
            mHelloTextView.setText("Hello");
        });
        buttonCounter.setOnClickListener((view) -> mHelloTextView.setText(getString(R.string.crow_counter, ++counter)));
    }
}