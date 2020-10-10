package com.example.day31_fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView description;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        description = findViewById(R.id.description);
        imageView = findViewById(R.id.imageView);
    }

    public void onButtonClick(View view) {
        switch (view.getId()){
            case R.id.angular:
                imageView.setImageResource(R.drawable.angular);
                description.setText(R.string.angular);
                return;
            case R.id.react:
                imageView.setImageResource(R.drawable.react);
                description.setText(R.string.react);
                return;
            case R.id.vue:
                imageView.setImageResource(R.drawable.vue);
                description.setText(R.string.vue);
        }
    }
}
