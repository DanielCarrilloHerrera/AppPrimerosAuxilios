package com.primerosauxilios.udec.appprimerosauxilios.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.primerosauxilios.udec.appprimerosauxilios.R;

public class FragmentoCaso extends Fragment {

    public FragmentoCaso() {
        // Required empty public constructor
    }

    public static FragmentoCaso newInstance(String param1, String param2) {
        FragmentoCaso fragment = new FragmentoCaso();
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
        return inflater.inflate(R.layout.fragmento_caso, container, false);
    }

}
