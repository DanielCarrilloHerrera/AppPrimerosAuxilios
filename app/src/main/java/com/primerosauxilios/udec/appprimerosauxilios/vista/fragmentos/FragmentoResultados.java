package com.primerosauxilios.udec.appprimerosauxilios.vista.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.primerosauxilios.udec.appprimerosauxilios.R;

import java.util.ArrayList;

public class FragmentoResultados extends ListFragment{

    Context context;
    Puente activity;
    ArrayList<String> listaCasos;

    public interface Puente {

        public void casoAMostrar(String nombreCaso);
    }

    public FragmentoResultados() {
        // Required empty public constructor
    }

    public static FragmentoResultados newInstance(String param1, String param2) {
        FragmentoResultados fragment = new FragmentoResultados();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Para verificar si la activity que contiene al fragmento
        //ha implementado la interfaz Puente
        try{
            activity = (Puente) context;
            this.context = context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "debe implementar la intefaz Puente");
        }

        setRetainInstance(true);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_resultados, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {//Enviar a la activity el item seleccionado
        activity.casoAMostrar(listaCasos.get(position));
    }

    public void llenarListaCasos(ArrayList<String> listaCasos){
        this.listaCasos = listaCasos;
        llenarListView();
    }

    private void llenarListView(){// Se procede a llenar el ListView con la lista de casos
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                                                                android.R.layout.simple_list_item_1,
                                                                listaCasos);
        setListAdapter(adapter);
    }
}
