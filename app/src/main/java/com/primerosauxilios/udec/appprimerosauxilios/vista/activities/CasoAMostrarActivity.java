package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Caso;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes;

public class CasoAMostrarActivity extends AppCompatActivity {

    private TextView tvTexto;
    private String nombreCaso;
    private Caso caso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medidas_generales);

        tvTexto = (TextView) findViewById(R.id.tvTexto);
        nombreCaso = getIntent().getStringExtra(DatabasePAConstantes.CASO);

        cargarCaso();
    }
//-------------------- CARGAR CASO MEDIDAS GENERALES EN LA VISTA--------------------------
    private void cargarCaso(){
        Aplicacion aplicacion = Aplicacion.getInstancia(getApplicationContext());
        caso = aplicacion.getCaso(nombreCaso);
        this.setTitle(caso.getNombre());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvTexto.setText(Html.fromHtml(caso.getProcedimiento(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvTexto.setText(Html.fromHtml(caso.getProcedimiento()));
        }
    }
}
