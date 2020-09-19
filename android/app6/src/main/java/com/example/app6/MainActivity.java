package com.example.app6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText key;
    private EditText value;
    private Map<String, String> data = new HashMap<>();
    private static final int SELECT_PAIR = 0;
    private static final String ACTION_RECEIVER_ACTIVITY = "com.example.app6.ReceiverActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        key = findViewById(R.id.key);
        value = findViewById(R.id.value);

    }

    public void acceptPair(View view) {
        if(key.length() > 0 && value.length() > 0) {
            data.put(key.getText().toString(), value.getText().toString());
            key.setText("");
            value.setText("");
        }
    }

    public void sendData(View view) {
        Intent intent = new Intent(ACTION_RECEIVER_ACTIVITY);
        intent.putExtra("data", (Serializable) data);
        startActivityForResult(intent, SELECT_PAIR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView selected = findViewById(R.id.selected);
        if(resultCode == RESULT_OK && requestCode == SELECT_PAIR && data != null) {
            String text = data.getStringExtra("selectedPair");
            if (text != null) {
                selected.setText(getString(R.string.selected, text));
            }
        } else {
            selected.setText("");
        }

    }
}
