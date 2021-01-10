package com.example.catalogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Splash extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timer=new Thread(){
            public void run(){
                try {
                    sleep(4000);
                }catch (Exception e){

                }finally {
                    Intent intent=new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                    Animatoo.animateSplit(Splash.this);
                    finish();
                }
            }
        };
        mp= MediaPlayer.create(this,R.raw.ray_gun);
        mp.start();
        timer.start();
    }
}