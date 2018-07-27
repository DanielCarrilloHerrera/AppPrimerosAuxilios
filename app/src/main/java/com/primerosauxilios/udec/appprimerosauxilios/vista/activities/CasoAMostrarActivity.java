package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Caso;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes;
import com.primerosauxilios.udec.appprimerosauxilios.vista.activities.adapters.ListaPasosCustomAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CasoAMostrarActivity extends AppCompatActivity {
    private static int unTiempo = 0;
    private SeekBar barraReproduccion;
    private ImageButton btnPlayPause;
    private Thread ActualizarTiempoAudio = new Hilo();
    private Caso caso;
    private Handler manejador;
    private String nombreCaso;
    private List<String> listaPasos;
    private List<Integer> iconosPasos;
    private ListaPasosCustomAdapter adapter;
    private boolean reproduciendoAudio;
    private MediaPlayer reproductor;
    private int tiempoAdelante = 5000;
    private double tiempoComienzo = 0;
    private int tiempoDetras = 5000;
    private double tiempoFinal = 0;
    private ListView lvPasos;

    @Override
    public void onBackPressed() {
        //Se deshabilita el boton de retroceso en el telefono
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // ReHome
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
        }catch (NullPointerException e){
            return;
        }
        setContentView(R.layout.activity_caso);
        this.lvPasos = (ListView) findViewById(R.id.lvPasos);
        this.lvPasos.setDividerHeight(0);
        this.lvPasos.setDivider(null);
        this.barraReproduccion = (SeekBar) findViewById(R.id.barraReproduccion);
        this.btnPlayPause = (ImageButton) findViewById(R.id.btnPlayPause);
        this.nombreCaso = getIntent().getStringExtra(DatabasePAConstantes.CASO);
        this.iconosPasos = obtenerIdsIconos();
        cargarCaso();
        String audio = this.caso.getAudioProcedimiento().toLowerCase();
        this.reproductor = MediaPlayer.create(this, getResources().getIdentifier(audio, "raw", getApplicationContext().getPackageName()));
        this.reproduciendoAudio = false;
        this.manejador = new Handler();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        barraReproduccion.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        //barraReproduccion.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
    }

    private List<Integer> obtenerIdsIconos() {
        ArrayList<Integer> images = new ArrayList<Integer>();

        for (int i = 0; i < 14; i++){
            int identifier = getResources().getIdentifier( "a"+(i+1), "drawable", this.getPackageName());
            images.add(identifier);
        }

        return images;
    }

    private void cargarCaso() {
        try {
            this.caso = Aplicacion.getInstancia(getApplicationContext()).getCaso(this.nombreCaso);
            this.listaPasos = obtenerPasosDeProcedimiento(this.caso.getProcedimiento());
            cargarListViewPasos(this.listaPasos);
        }
        catch (NullPointerException e){
            return;
        }
        setTitle(this.caso.getNombre());
        /*if (Build.VERSION.SDK_INT >= 24) {
            this.tvTexto.setText(Html.fromHtml(this.caso.getProcedimiento(), 0));
        } else {
            this.tvTexto.setText(Html.fromHtml(this.caso.getProcedimiento()));
        }*/


        // Se recupera el tamaño de la letra almacenado en el SharedPreferences y se le asigna al textview del texto
        /*
        Context contexto = getApplicationContext();

        SharedPreferences sharedPreferences =
                contexto.getSharedPreferences(getString(R.string.tamañoLetra),
                        contexto.MODE_PRIVATE);

        int tamañoLetra = sharedPreferences.getInt(getString(R.string.tamañoLetra), 15);*/

        //tvTexto.setTextSize(tamañoLetra);


    }

    private void cargarListViewPasos(List<String> listaPasos) {
        this.adapter = new ListaPasosCustomAdapter(this, this.listaPasos, this.iconosPasos);
        this.lvPasos.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    private List<String> obtenerPasosDeProcedimiento(String procedimiento) {

        ArrayList<String> pasos = new ArrayList<>(Arrays.asList(procedimiento.split("(\\<(b|p|h1)\\>){0,1}[\\s]{0,1}[\\d]{1,3}\\.[\\s]{0,3}")));
        pasos.remove("");
        return pasos;
    }

    public void botonesAudio(View view) {
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
                    Toast.makeText(getApplicationContext(), "Pausando",Toast.LENGTH_SHORT).show();
                    this.reproductor.pause();
                    reproduciendoAudio = false;
                    btnPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    return;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Reproduciendo audio...", Toast.LENGTH_SHORT).show();
                    this.reproductor.start();
                    barraReproduccion.setMax(reproductor.getDuration());
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


    protected void onStop() {
        super.onStop();
        if(!this.ActualizarTiempoAudio.isInterrupted()) {
            this.reproductor.stop();
            this.ActualizarTiempoAudio.interrupt();
            this.ActualizarTiempoAudio = null;
        }
    }

    class Hilo extends Thread {
        private boolean ejecutarse = true;

        Hilo() {
        }

        public void run() {
            if (!this.ejecutarse) {
                CasoAMostrarActivity.this.tiempoComienzo = (double) CasoAMostrarActivity.this.reproductor.getCurrentPosition();
            }
            barraReproduccion.setProgress(reproductor.getCurrentPosition());
            CasoAMostrarActivity.this.manejador.postDelayed(this, 100);
        }
    }
}
