package com.primerosauxilios.udec.appprimerosauxilios.vista;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.primerosauxilios.udec.appprimerosauxilios.R;

public class FragmentoResultados extends ListFragment implements AdapterView.OnItemClickListener {

    public FragmentoResultados() {
        // Required empty public constructor
    }

    public static FragmentoResultados newInstance(String param1, String param2) {
        FragmentoResultados fragment = new FragmentoResultados();
        return fragment;
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
