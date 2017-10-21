package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.MediaController.MediaPlayerControl;

import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Caso;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes;
import com.primerosauxilios.udec.appprimerosauxilios.vista.activities.extras.AudioController;

public class CasoAMostrarActivity extends AppCompatActivity implements  MediaPlayerControl{

    private TextView tvTexto;
    private String nombreCaso;
    private Caso caso;
    private AudioController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso);

        tvTexto = (TextView) findViewById(R.id.tvTexto);
        nombreCaso = getIntent().getStringExtra(DatabasePAConstantes.CASO);

        cargarCaso();

        setController();
    }
//-------------------- CARGAR CASO MEDIDAS GENERALES EN LA VISTA--------------------------
    private void cargarCaso(){
        Aplicacion aplicacion = Aplicacion.getInstancia(getApplicationContext());
        caso = aplicacion.getCaso(nombreCaso);

        /*reproductor = MediaPlayer.create(this, getResources().getIdentifier(caso.getAudioProcedimiento(),
                                                                            "raw",
                                                                            getPackageName())); // Se carga el audio en el
        */                                                                                        //objeto MediaPlaye

        this.setTitle(caso.getNombre());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvTexto.setText(Html.fromHtml(caso.getProcedimiento(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvTexto.setText(Html.fromHtml(caso.getProcedimiento()));
        }
    }
//-------------------- AUDIO CONTROLLER --------------------------------------------------
    private void setController(){
        //preparar el controlador de audio
        controller = new AudioController(this);

        controller.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playNext();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPrev();
            }
        });

        controller.setMediaPlayer(this);
        controller.setAnchorView(findViewById(R.id.tvTexto));
        controller.setEnabled(true);
    }

}
