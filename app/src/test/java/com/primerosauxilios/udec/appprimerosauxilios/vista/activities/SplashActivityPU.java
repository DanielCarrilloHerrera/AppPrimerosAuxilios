package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.content.Intent;
import android.os.Handler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daniel on 29/11/17.
 */
public class SplashActivityPU {
    private Handler retardador;
    @Test
    public void onCreate() throws Exception {
        this.retardador = new Handler();
        this.retardador.postDelayed(new SplashActivityPU.Hilo(), 3000);
    }

    class Hilo implements Runnable {
        Hilo() {
        }
        @Test
        public void run() {
        }
    }

}