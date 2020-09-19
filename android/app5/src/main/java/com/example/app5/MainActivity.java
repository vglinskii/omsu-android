package com.example.app5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewJS;
    private String[] textViewJSVariants = {"JS", "JavaScript", "JScript"};

    private TextView textViewJava;
    private String[] textViewJavaVariants = {"Java", "java"};

    private TextView textViewPython;
    private String[] textViewPythonVariants = {"Python", "Py"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewJS = findViewById(R.id.textView_JS);
        textViewJava = findViewById(R.id.textView_Java);
        textViewPython = findViewById(R.id.textView_Python);

    }

    public void onJavaClick(View view) {
        textViewJS.setText(textViewJSVariants[(int)(Math.random() * textViewJSVariants.length)]);
        textViewJava.setText(textViewJavaVariants[(int)(Math.random() * textViewJavaVariants.length)]);
        textViewPython.setText(textViewPythonVariants[(int)(Math.random() * textViewPythonVariants.length)]);
    }
}
