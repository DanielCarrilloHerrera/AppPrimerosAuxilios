package com.primerosauxilios.udec.appprimerosauxilios.logica;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePA;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.PrimerosAuxiliosDBHelper;

import java.util.ArrayList;

/**
 * Created by daniel on 3/10/17.
 */

public class Aplicacion {

    private Context context;
    private static Aplicacion instancia;
    private ArrayList<Caso> listaCasos;
    private PrimerosAuxiliosDBHelper admin;


    private Aplicacion(Context context){
        listaCasos = new ArrayList<Caso>();
        this.context = context;
        admin = new PrimerosAuxiliosDBHelper(context);
    }

    public ArrayList<Caso> getListaCasos() {
        return listaCasos;
    }

    public static Aplicacion getInstancia(Context context) {//Singleton

        if(instancia == null)
            instancia = new Aplicacion(context);

        return instancia;
    }

    public Caso getCaso(int id){
        return null;
    }

//----------------- CONSULTAR CASO SEGÚN EL NOMBRE  ------------------------
    public Caso getCaso(String nombre){

        SQLiteDatabase db = admin.getWritableDatabase();
        Caso retorno = new Caso();

        Cursor caso = db.rawQuery(
                "SELECT "+ DatabasePA.EntryCasos._ID + ", " +
                DatabasePA.EntryCasos.NOMBRE + ", " +
                DatabasePA.EntryCasos.PROCEDIMIENTO + ", " +
                DatabasePA.EntryCasos.PALABRAS_CLAVES_BUSQUEDA + ", " +
                DatabasePA.EntryCasos.ARCHIVO_AUDIO +" FROM " +
                DatabasePA.EntryCasos.TABLA_CASOS+" WHERE " +
                DatabasePA.EntryCasos.NOMBRE + " LIKE '%" + nombre +"%' ", null);


        caso.moveToFirst();
        retorno.setId(caso.getInt(0));
        retorno.setNombre(caso.getString(1));
        retorno.asignarProcedimiento(caso.getString(2));
        retorno.agregarPalabraClave(caso.getString(3));
        retorno.asignarAudioAProcedimiento(caso.getString(4));

        return retorno;
    }

//------------------------------------------------------------------------------------
    public ArrayList<Caso> getCasos(ArrayList<String> palabraClave){
        return null;
    }

    public String agregarNuevoCaso(String nombreCaso){
        return null;
    }

    public String eliminarCaso(String nombreCaso){ return null; }

}
