package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Caso;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnQueryTextListener, OnItemClickListener {
    ArrayAdapter<String> adapter;
    ArrayList<String> listaCasos;
    ListView lvResultados;
    SearchView simpleSearchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.simpleSearchView = (SearchView) findViewById(R.id.simpleSearchView);
        this.simpleSearchView.setOnQueryTextListener(this);
        this.lvResultados = (ListView) findViewById(R.id.listaResultados);
        this.listaCasos = new ArrayList();
        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.listaCasos);
        this.lvResultados.setAdapter(this.adapter);
        this.lvResultados.setOnItemClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemMedidasGenerales:
                Intent intent = new Intent(this, CasoAMostrarActivity.class);
                intent.putExtra(DatabasePAConstantes.CASO, DatabasePAConstantes.CASO_MEDIDAS_GENERALES);
                startActivity(intent);
                return true;

            case R.id.itemTamañoLetra:
                dialogoTamañoLetra();
                return true;
            case R.id.itemMarcoLegal:
                dialogoMarcoLegal();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void dialogoTamañoLetra(){

        //Inicializar el Alert Dialog

        AlertDialog.Builder dialogo = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
        dialogo.setTitle("Tamaño de letra");
        dialogo.setMessage("Seleccione el tamaño de la letra");
        dialogo.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialogo_tamaño_letra);
        numberPicker.setMaxValue(50);
        numberPicker.setMinValue(15);
        numberPicker.setWrapSelectorWheel(false);

        //Se recupera el valor almacenado de la letra, se le asigna al NumberPicker como valor por defecto
        //y se procede a codificar para almacenar el nuevo valor que se seleccione en el SharedPreferences

        Context contexto = getApplicationContext();
        SharedPreferences sharedPreferences =
                contexto.getSharedPreferences(getString(R.string.tamañoLetra),
                        contexto.MODE_PRIVATE);

        int tamañoLetra = sharedPreferences.getInt(getString(R.string.tamañoLetra), 0);

        numberPicker.setValue(tamañoLetra);

        final SharedPreferences.Editor editor = sharedPreferences.edit();

        dialogo.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editor.putInt(getString(R.string.tamañoLetra), numberPicker.getValue());
                editor.commit();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editor.commit();
            }
        });
        AlertDialog alertDialog = dialogo.create();
        alertDialog.show();
    }

    public void dialogoMarcoLegal(){
        //Inicializar el Alert Dialog

        AlertDialog.Builder dialogo = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        Caso casoMarcoLegal = Aplicacion.getInstancia(getApplicationContext()).getCaso("Marco Legal");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.marco_legal_dialog, null);
        dialogo.setTitle(casoMarcoLegal.getNombre());
        TextView texto = (TextView) dialogView.findViewById(R.id.tvTextoMarcoLegal);

        //formato al texto
        if (Build.VERSION.SDK_INT >= 24) {
            texto.setText(Html.fromHtml(casoMarcoLegal.getProcedimiento(), 0));
        } else {
            texto.setText(Html.fromHtml(casoMarcoLegal.getProcedimiento()));
        }

        dialogo.setView(dialogView);

        dialogo.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = dialogo.create();
        alertDialog.show();
    }

    public boolean onQueryTextSubmit(String query) {
        this.listaCasos = Aplicacion.getInstancia(getApplicationContext()).getNombresCasos(query);
        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.listaCasos);
        this.lvResultados.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
        return false;
    }

    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String casoSeleccionado = this.lvResultados.getAdapter().getItem(i).toString();
        Intent intent = new Intent(this, CasoAMostrarActivity.class);
        intent.putExtra(DatabasePAConstantes.CASO, casoSeleccionado);
        startActivity(intent);
    }
}
