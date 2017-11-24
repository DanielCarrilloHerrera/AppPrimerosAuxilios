package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Caso;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daniel on 18/11/17.
 */
public class CasoAMostrarActivityPU{

    private Context context;
    private static int unTiempo = 0;
    private ImageButton btnPlayPause;
    private Thread ActualizarTiempoAudio = new CasoAMostrarActivityPU.Hilo();
    private Caso caso;
    private Handler manejador;
    private String nombreCaso;
    private boolean reproduciendoAudio;
    private MediaPlayer reproductor;
    private int tiempoAdelante = 5000;
    private double tiempoComienzo = 0;
    private int tiempoDetras = 5000;
    private double tiempoFinal = 0;
    private TextView tvTexto;


    @Test
    public void cargarCaso() {
        this.caso = Aplicacion.getInstancia(context).getCaso(this.nombreCaso);
        if (Build.VERSION.SDK_INT >= 24) {
            this.tvTexto.setText(Html.fromHtml(this.caso.getProcedimiento(), 0));
        } else {
            this.tvTexto.setText(Html.fromHtml(this.caso.getProcedimiento()));
        }


        // Se recupera el tamaño de la letra almacenado en el SharedPreferences y se le asigna al textview del texto

        Context contexto = context;

        SharedPreferences sharedPreferences =
                contexto.getSharedPreferences(contexto.getString(R.string.tamañoLetra),
                        contexto.MODE_PRIVATE);

        int tamañoLetra = sharedPreferences.getInt(contexto.getString(R.string.tamañoLetra), 0);

        tvTexto.setTextSize(tamañoLetra);

    }

    @Test
    public void botonesAudio() {
        View view = new View(context);
        switch (view.getId()) {
            case R.id.btnRetroceder:
                if (((int) this.tiempoComienzo) - this.tiempoDetras > 0) {
                    this.tiempoComienzo -= (double) this.tiempoDetras;
                    this.reproductor.seekTo((int) this.tiempoComienzo);
                    return;
                }
                return;
            case R.id.btnPlayPause:
                if (this.reproduciendoAudio) {
                    Toast.makeText(context, "Pausando",Toast.LENGTH_SHORT).show();
                    this.reproductor.pause();
                    reproduciendoAudio = false;
                    btnPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    return;
                }
                else {
                    Toast.makeText(context, "Reproduciendo audio...", Toast.LENGTH_SHORT).show();
                    this.reproductor.start();
                    this.tiempoFinal = (double) this.reproductor.getDuration();
                    this.tiempoComienzo = (double) this.reproductor.getCurrentPosition();
                    if (unTiempo == 0) {
                        unTiempo = 1;
                    }
                    reproduciendoAudio = true;
                }
                this.manejador.postDelayed(this.ActualizarTiempoAudio, 100);
                btnPlayPause.setImageResource(R.drawable.ic_pause_black_24dp);
                return;
            case R.id.btnAdelantar:
                if (((double) (this.tiempoAdelante + ((int) this.tiempoComienzo))) <= this.tiempoFinal) {
                    this.tiempoComienzo += (double) this.tiempoAdelante;
                    this.reproductor.seekTo((int) this.tiempoComienzo);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Test
    public void onStop() {

        this.ActualizarTiempoAudio.interrupt();
        this.ActualizarTiempoAudio = null;
        this.reproductor.stop();
    }

    class Hilo extends Thread {
        private boolean ejecutarse = true;

        Hilo() {
        }
    @Test
        public void run() {
            if (!this.ejecutarse) {
                CasoAMostrarActivityPU.this.tiempoComienzo = (double) CasoAMostrarActivityPU.this.reproductor.getCurrentPosition();
            }
            CasoAMostrarActivityPU.this.manejador.postDelayed(this, 100);
        }
    }
}