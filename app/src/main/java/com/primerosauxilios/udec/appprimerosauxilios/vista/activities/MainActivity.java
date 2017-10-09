package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    SearchView simpleSearchView;
    ListView lvResultados;
    ArrayList<String> listaCasos;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleSearchView = (SearchView) findViewById(R.id.simpleSearchView); //initiate a search view
        simpleSearchView.setOnQueryTextListener(this);

        lvResultados = (ListView) findViewById(R.id.listaResultados);

        listaCasos = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listaCasos);
        lvResultados.setAdapter(adapter);
        lvResultados.setOnItemClickListener(this);

    }


    public void medidasGenerales(View v){//Caso Medidas Generales
        Intent intent = new Intent(this, CasoAMostrarActivity.class);
        intent.putExtra(DatabasePAConstantes.CASO,DatabasePAConstantes.CASO_MEDIDAS_GENERALES);
        startActivity(intent);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Aplicacion aplicacion = Aplicacion.getInstancia(getApplicationContext());
        this.listaCasos = aplicacion.getNombresCasos(query);//Se obtienen de la base de datos el listado de casos
                                                                        //de acuerdo a las palabras ingresadas en el SearchView


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                this.listaCasos);
        lvResultados.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String casoSeleccionado = lvResultados.getAdapter().getItem(i).toString();Intent intent = new Intent(this, CasoAMostrarActivity.class);
        intent.putExtra(DatabasePAConstantes.CASO,casoSeleccionado);
        startActivity(intent);

    }
}
