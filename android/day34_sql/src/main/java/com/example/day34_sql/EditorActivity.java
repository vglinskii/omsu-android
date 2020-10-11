package com.example.day34_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.day34_sql.data.HotelContract;
import com.example.day34_sql.data.HotelDbHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class EditorActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText cityEditText;
    private EditText ageEditText;
    private Spinner genderSpinner;
    private int gender = 2;

    HotelDbHelper hotelDbHelper;

    public EditorActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        hotelDbHelper = new HotelDbHelper(this);

        nameEditText = (EditText) findViewById(R.id.edit_guest_name);
        cityEditText = (EditText) findViewById(R.id.edit_guest_city);
        ageEditText = (EditText) findViewById(R.id.edit_guest_age);
        genderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        setupSpinner();
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        genderSpinner.setAdapter(genderSpinnerAdapter);
        genderSpinner.setSelection(2);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_female))) {
                        gender = HotelContract.GuestEntry.GENDER_FEMALE; // Кошка
                    } else if (selection.equals(getString(R.string.gender_male))) {
                        gender = HotelContract.GuestEntry.GENDER_MALE; // Кот
                    } else {
                        gender = HotelContract.GuestEntry.GENDER_UNKNOWN; // Не определен
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = 2;
            }
        });
    }

    private void insertGuest() {
        String name = nameEditText.getText().toString().trim();
        String city = cityEditText.getText().toString().trim();
        String ageString = ageEditText.getText().toString().trim();
        int age = Integer.parseInt(ageString);

        SQLiteDatabase db = hotelDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HotelContract.GuestEntry.COLUMN_NAME, name);
        values.put(HotelContract.GuestEntry.COLUMN_CITY, city);
        values.put(HotelContract.GuestEntry.COLUMN_GENDER, gender);
        values.put(HotelContract.GuestEntry.COLUMN_AGE, age);

        long newRowId = db.insert(HotelContract.GuestEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Ошибка при заведении гостя",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Гость заведён под номером: " + newRowId,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteGuest() {
        String name = nameEditText.getText().toString().trim();
        String city = cityEditText.getText().toString().trim();
        String ageString = ageEditText.getText().toString().trim();
        String genderString = String.valueOf(gender);

        SQLiteDatabase db = hotelDbHelper.getWritableDatabase();

        String whereClause = HotelContract.GuestEntry.COLUMN_NAME + " = ?" +
                " AND " +
                HotelContract.GuestEntry.COLUMN_CITY + " = ?" +
                " AND " +
                HotelContract.GuestEntry.COLUMN_GENDER + " = ?" +
                " AND " +
                HotelContract.GuestEntry.COLUMN_AGE + " = ?";
        int rowsDeleted = db.delete(
                HotelContract.GuestEntry.TABLE_NAME,
                whereClause,
                new String[] {name, city, genderString, ageString});

        Toast.makeText(this, "Удалено " + rowsDeleted + " строк",
                Toast.LENGTH_SHORT).show();
    }

    public void onInsert(View view) {
        insertGuest();
        finish();
    }

    public void onDelete(View view) {
        deleteGuest();
        finish();
    }

}