package com.example.day18_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView sun;
    Animation sunRise;
    ImageView clock;
    Animation clock_turn;
    ImageView hourArrow;
    Animation hour_turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sun = findViewById(R.id.sun);
        sunRise = AnimationUtils.loadAnimation(this, R.anim.sun_rise);

        clock = findViewById(R.id.clock);
        clock_turn = AnimationUtils.loadAnimation(this, R.anim.clock_turn);

        hourArrow = findViewById(R.id.hourArrow);
        hour_turn = AnimationUtils.loadAnimation(this, R.anim.hour_turn);
    }

    public void startAnimation(View view) {
        sun.startAnimation(sunRise);
        clock.startAnimation(clock_turn);
        hourArrow.startAnimation(hour_turn);
    }
}
