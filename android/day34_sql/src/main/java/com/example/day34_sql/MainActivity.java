package com.example.day34_sql;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.day34_sql.data.HotelContract;
import com.example.day34_sql.data.HotelDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private HotelDbHelper hotelDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button openEditorBtn = findViewById(R.id.openEditor);
        openEditorBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            startActivity(intent);
        });

        hotelDbHelper = new HotelDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_new_data:
                insertGuest();
                displayDatabaseInfo();
                return true;
            case R.id.action_delete_all_entries:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = hotelDbHelper.getReadableDatabase();

        String[] projection = {
                HotelContract.GuestEntry._ID,
                HotelContract.GuestEntry.COLUMN_NAME,
                HotelContract.GuestEntry.COLUMN_CITY,
                HotelContract.GuestEntry.COLUMN_GENDER,
                HotelContract.GuestEntry.COLUMN_AGE};

        Cursor cursor = db.query(
                HotelContract.GuestEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayTextView = (TextView) findViewById(R.id.text_view_info);

        try {
            displayTextView.setText("Таблица содержит " + cursor.getCount() + " гостей.\n\n");
            displayTextView.append(HotelContract.GuestEntry._ID + " - " +
                    HotelContract.GuestEntry.COLUMN_NAME + " - " +
                    HotelContract.GuestEntry.COLUMN_CITY + " - " +
                    HotelContract.GuestEntry.COLUMN_GENDER + " - " +
                    HotelContract.GuestEntry.COLUMN_AGE + "\n");

            int idColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_NAME);
            int cityColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_CITY);
            int genderColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_GENDER);
            int ageColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_AGE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentCity = cursor.getString(cityColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentAge = cursor.getInt(ageColumnIndex);

                displayTextView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentCity + " - " +
                        getGenderString(currentGender) + " - " +
                        currentAge));
            }
        } finally {
            cursor.close();
        }
    }

    private void insertGuest() {
        SQLiteDatabase db = hotelDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HotelContract.GuestEntry.COLUMN_NAME, "Мурзик");
        values.put(HotelContract.GuestEntry.COLUMN_CITY, "Мурманск");
        values.put(HotelContract.GuestEntry.COLUMN_GENDER, HotelContract.GuestEntry.GENDER_MALE);
        values.put(HotelContract.GuestEntry.COLUMN_AGE, 7);

        db.insert(HotelContract.GuestEntry.TABLE_NAME, null, values);
    }

    private String getGenderString(int gender) {
        switch (gender) {
            case 0:
                return getString(R.string.gender_female);
            case 1:
                return getString(R.string.gender_male);
        }
        return getString(R.string.gender_unknown);
    }
}
