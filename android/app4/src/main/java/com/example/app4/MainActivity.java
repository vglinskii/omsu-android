package com.example.app4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.constraintLayout);
        textView = findViewById(R.id.textView);
        textView.setText(R.string.red);
        constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorRed));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.redButton:
                textView.setText(R.string.red);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorRed));
                break;
            case R.id.yellowButton:
                textView.setText(R.string.yellow);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                break;
            case R.id.greenButton:
                textView.setText(R.string.green);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                break;
        }
    }
}
