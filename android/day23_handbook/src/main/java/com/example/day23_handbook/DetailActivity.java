package com.example.day23_handbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WebView webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        String resName = "n" + intent.getIntExtra("title", 0);

        String text = readRawTextFile(getBaseContext(), getResources().getIdentifier(resName,
                "raw", "com.example.day23_handbook"));

        webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);
    }

    private String readRawTextFile(Context context, int resId) {
        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resId)))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = buffReader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            return "";
        }

    }
}