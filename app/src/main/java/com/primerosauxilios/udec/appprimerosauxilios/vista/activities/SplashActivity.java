package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.primerosauxilios.udec.appprimerosauxilios.R;

public class SplashActivity extends AppCompatActivity {
    private Handler retardador;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        this.retardador = new Handler();
        this.retardador.postDelayed(new Hilo(), 3000);
    }

    class Hilo implements Runnable {
        Hilo() {
        }

        public void run() {
            SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            SplashActivity.this.finish();
        }
    }
}
