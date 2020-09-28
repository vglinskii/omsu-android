package com.example.day15_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private RadioButton dollarsButton;
    private RadioButton rublesButton;
    private final double EXCHANGE_RATE = 78.67;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        dollarsButton = findViewById(R.id.radioButtonDollars);
        rublesButton = findViewById(R.id.radioButtonRubles);
    }

    public void onClick(View view) {
        if(editText.getText().length() == 0) {
            Toast.makeText(this, getString(R.string.enter_value), Toast.LENGTH_SHORT).show();
            return;
        }
        double inputValue = Double.parseDouble(editText.getText().toString());
        if(dollarsButton.isChecked()) {
            editText.setText(String.valueOf(convertRublesToDollars(inputValue)));
        } else {
            editText.setText(String.valueOf(convertDollarsToRubles(inputValue)));
        }
    }

    private double convertRublesToDollars(double rubles) {
        return rubles * EXCHANGE_RATE;
    }

    private double convertDollarsToRubles(double dollars) {
        return dollars / EXCHANGE_RATE;
    }
}
