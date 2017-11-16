package com.primerosauxilios.udec.appprimerosauxilios.vista.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.primerosauxilios.udec.appprimerosauxilios.R;
import com.primerosauxilios.udec.appprimerosauxilios.logica.Aplicacion;
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
            default:
                return super.onOptionsItemSelected(item);
        }
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
