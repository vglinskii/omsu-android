package com.example.day11_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_profile:
                Snackbar.make(findViewById(R.id.constraint_layout), R.string.choose_profile, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.menu_language:
                Snackbar.make(findViewById(R.id.constraint_layout), R.string.choose_language, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.menu_volume:
                Snackbar.make(findViewById(R.id.constraint_layout), R.string.choose_volume, Snackbar.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
