package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Caso;

public class MedidasGeneralesPA extends AppCompatActivity {

    private TextView tvTexto;
    private Caso casoMedidasGeneralesPA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medidas_generales);

        tvTexto = (TextView) findViewById(R.id.tvTexto);

        cargarCasoMedidasGenerales();
    }
//-------------------- CARGAR CASO MEDIDAS GENERALES EN LA VISTA--------------------------
    private void cargarCasoMedidasGenerales(){
        Aplicacion aplicacion = Aplicacion.getInstancia(getApplicationContext());
        casoMedidasGeneralesPA = aplicacion.getCaso("Medidas Generales de Primeros Auxilios");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvTexto.setText(Html.fromHtml(casoMedidasGeneralesPA.getProcedimiento(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvTexto.setText(Html.fromHtml(casoMedidasGeneralesPA.getProcedimiento()));
        }
    }
}
