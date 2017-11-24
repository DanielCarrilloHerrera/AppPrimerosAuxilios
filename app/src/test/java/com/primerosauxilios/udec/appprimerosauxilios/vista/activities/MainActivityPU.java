package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;

import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by daniel on 19/11/17.
 */
public class MainActivityPU {
    ArrayAdapter<String> adapter;
    ArrayList<String> listaCasos;
    ListView lvResultados;
    SearchView simpleSearchView;
    Context context;
    Bundle savedInstanceState;
    MenuItem item;

    @Test
    public void onCreate() {
        this.adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, this.listaCasos);
        this.lvResultados.setAdapter(this.adapter);
    }

    @Test
    public void dialogoTamañoLetra(){

        //Inicializar el Alert Dialog

        AlertDialog.Builder dialogo = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        LayoutInflater inflater = new LayoutInflater(context) {
            @Override
            public LayoutInflater cloneInContext(Context context) {
                return null;
            }
        };

        View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
        dialogo.setTitle("Tamaño de letra");
        dialogo.setMessage("Seleccione el tamaño de la letra");
        dialogo.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialogo_tamaño_letra);
        numberPicker.setMaxValue(50);
        numberPicker.setMinValue(10);
        numberPicker.setWrapSelectorWheel(false);

        //Se recupera el valor almacenado de la letra, se le asigna al NumberPicker como valor por defecto
        //y se procede a codificar para almacenar el nuevo valor que se seleccione en el SharedPreferences

        Context contexto = context;
        SharedPreferences sharedPreferences =
                contexto.getSharedPreferences("",
                        contexto.MODE_PRIVATE);

        int tamañoLetra = sharedPreferences.getInt("", 0);

        numberPicker.setValue(tamañoLetra);

        final SharedPreferences.Editor editor = sharedPreferences.edit();

        dialogo.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editor.putInt("", numberPicker.getValue());
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

    @Test
    public void onQueryTextSubmit(String query) {
        this.listaCasos = Aplicacion.getInstancia(context).getNombresCasos(query);
        this.adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, this.listaCasos);
        this.lvResultados.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String casoSeleccionado = this.lvResultados.getAdapter().getItem(i).toString();
        Intent intent = new Intent(context, CasoAMostrarActivity.class);
        intent.putExtra(DatabasePAConstantes.CASO, casoSeleccionado);

    }

}