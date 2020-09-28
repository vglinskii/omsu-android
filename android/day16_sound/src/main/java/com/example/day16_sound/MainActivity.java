package com.example.day16_sound;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private AssetManager assetManager;
    private Map<String, Integer> sounds = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonHello = findViewById(R.id.buttonHello);
        buttonHello.setOnClickListener(onClickListener);

        Button buttonGoodDay = findViewById(R.id.buttonGoodDay);
        buttonGoodDay.setOnClickListener(onClickListener);

        Button buttonBye = findViewById(R.id.buttonBye);
        buttonBye.setOnClickListener(onClickListener);

        Button buttonHaveANiceDay = findViewById(R.id.buttonHaveANiceDay);
        buttonHaveANiceDay.setOnClickListener(onClickListener);

        Button buttonHowAreYou = findViewById(R.id.buttonHowAreYou);
        buttonHowAreYou.setOnClickListener(onClickListener);

        Button buttonThanks = findViewById(R.id.buttonThanks);
        buttonThanks.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonHello:
                    playSound(sounds.get("hello"));
                    break;
                case R.id.buttonBye:
                    playSound(sounds.get("bye"));
                    break;
                case R.id.buttonGoodDay:
                    playSound(sounds.get("goodDay"));
                    break;
                case R.id.buttonHaveANiceDay:
                    playSound(sounds.get("haveANiceDay"));
                    break;
                case R.id.buttonHowAreYou:
                    playSound(sounds.get("howAreYou"));
                    break;
                case R.id.buttonThanks:
                    playSound(sounds.get("thanks"));
                    break;
            }
        }
    };

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    private void playSound(Integer sound) {
        if (sound != null && sound > 0) {
            soundPool.play(sound, 1, 1, 1, 0, 1);
        }
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return soundPool.load(afd, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        createSoundPool();

        assetManager = getAssets();

        sounds.put("hello", loadSound("hello.mp3"));
        sounds.put("bye", loadSound("bye.mp3"));
        sounds.put("goodDay", loadSound("goodDay.mp3"));
        sounds.put("haveANiceDay", loadSound("haveANiceDay.mp3"));
        sounds.put("howAreYou", loadSound("howAreYou.mp3"));
        sounds.put("thanks", loadSound("thanks.mp3"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        soundPool.release();
        soundPool = null;
    }
}
