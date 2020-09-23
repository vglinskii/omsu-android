package com.example.day9_toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView toastStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastStatus = findViewById(R.id.toast_status);
        toastStatus.setText(R.string.toast_hidden);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void showToast(View view) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.my_toast_container));
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.addCallback(new Toast.Callback(){
                    @Override
                    public void onToastShown() {
                        super.onToastShown();
                        toastStatus.setText(R.string.toast_showed);
                    }

                    @Override
                    public void onToastHidden() {
                        super.onToastHidden();
                        toastStatus.setText(R.string.toast_hidden);
                    }
                });
                toast.show();
            }
        });

    }
}
