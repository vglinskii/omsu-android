package com.example.day8_themes_and_styles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAboutClick(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void onOpenDialogClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogAlertTheme);
        builder.setTitle("Диалоговое окно");
        builder.setMessage("Продолжить?");
        builder.setNegativeButton("Нет", null);
        builder.setPositiveButton("Да", null);
        builder.show();
    }
}
