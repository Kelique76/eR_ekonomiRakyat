package com.kelique.rcapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class SplashScreenActivity extends AppCompatActivity {


    @InjectView(R.id.pulsator)
    PulsatorLayout pulsator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.inject(this);
        pulsator.start();
        Handler penahan = new Handler();
        penahan.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
            }
        },5000);


    }
}
