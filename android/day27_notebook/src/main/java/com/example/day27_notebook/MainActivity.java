package com.example.day27_notebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final static String NOTEBOOK_FILENAME = "myNotebook.txt";
    private EditText notebookText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notebookText = findViewById(R.id.notebookText);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        processOpenMode(preferences);

        processTextSize(preferences);

       processTextStyle(preferences);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open:
                openFile(NOTEBOOK_FILENAME);
                break;
            case R.id.action_save:
                saveFile(NOTEBOOK_FILENAME);
                break;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void processOpenMode(SharedPreferences preferences) {
        if (preferences != null && preferences.getBoolean(getString(R.string.pref_openmode), false)) {
            openFile(NOTEBOOK_FILENAME);
        }
    }

    private void processTextSize(SharedPreferences preferences) {
        if (preferences != null) {
            String fontSizeString = preferences.getString(getString(R.string.pref_size), "20");
            if (fontSizeString != null) {
                float fontSize = Float.parseFloat(fontSizeString);
                notebookText.setTextSize(fontSize);
            }
        }
    }

    private void processTextStyle(SharedPreferences preferences) {
        if(preferences == null) {
            return;
        }
        String regular = preferences.getString(getString(R.string.pref_style), "");
        if (regular != null) {
            int typeface = Typeface.NORMAL;

            if (regular.contains("Полужирный"))
                typeface += Typeface.BOLD;

            if (regular.contains("Курсив"))
                typeface += Typeface.ITALIC;

            notebookText.setTypeface(null, typeface);
        }
    }

    private void openFile(String fileName) {
        try (InputStream inputStream = openFileInput(fileName);
             InputStreamReader isr = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(isr)) {

            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            notebookText.setText(builder.toString());
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),
                    "File not found", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void saveFile(String fileName) {
        try (OutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
             OutputStreamWriter osw = new OutputStreamWriter(outputStream);) {

            osw.write(notebookText.getText().toString());
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),
                    "File not found", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
