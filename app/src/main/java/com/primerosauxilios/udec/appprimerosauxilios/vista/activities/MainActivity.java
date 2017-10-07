package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
import com.primerosauxilios.udec.appprimerosauxilios.vista.fragmentos.FragmentoResultados;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, FragmentoResultados.Puente {

    SearchView simpleSearchView;
    FragmentoResultados fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleSearchView = (SearchView) findViewById(R.id.simpleSearchView); //initiate a search view
        simpleSearchView.setOnQueryTextListener(this);

        FragmentoResultados fr = (FragmentoResultados) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentoResultados);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);*/

        //Asociar configuración searchable con el SearchView
        /*SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));*/

        return true;
    }

    public void medidasGenerales(View v){
        Intent intent = new Intent(this, MedidasGeneralesPA.class);
        startActivity(intent);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Aplicacion aplicacion = Aplicacion.getInstancia(getApplicationContext());
        ArrayList<String> listaCasos = aplicacion.getNombresCasos(query);//Se obtienen de la base de datos el listado de casos
                                                                        //de acuerdo a las palabras ingresadas en el SearchView

        if (fr != null){//Si fr ya esta inicializado
            fr.llenarListaCasos(listaCasos);
            return true;
        } else {
            fr = new FragmentoResultados();
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.fragmentoResultados, fr);
            transaction.addToBackStack(null);
            fr.llenarListaCasos(listaCasos);
            return true;
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void casoAMostrar(String nombreCaso) {

    }
}
