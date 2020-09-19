package com.example.app6;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

public class ReceiverActivity extends Activity {

    private LinearLayout linearLayout;
    private Map<String, String> data = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_receiver);

        linearLayout = findViewById(R.id.list);

        data = (Map<String, String>) getIntent().getSerializableExtra("data");
        if(data != null) {
            generateList(data);
        }
    }

    private void generateList(Map<String, String> map) {
        for(Map.Entry<String, String> entry : map.entrySet()){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(getString(R.string.key_value, entry.getKey(), entry.getValue()));
            radioButton.setOnClickListener(this::onRadioClick);
            linearLayout.addView(radioButton);
        }
    }

    private void onRadioClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("selectedPair", ((RadioButton)view).getText());
        setResult(RESULT_OK, intent);
        finish();
    }
}
