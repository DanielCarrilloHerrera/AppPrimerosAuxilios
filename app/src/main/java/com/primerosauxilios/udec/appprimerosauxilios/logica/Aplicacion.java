package com.primerosauxilios.udec.appprimerosauxilios.logica;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.PrimerosAuxiliosDBHelper;

import java.util.ArrayList;

/**
 * Created by daniel on 3/10/17.
 */

public class Aplicacion {

    private Context context;
    private static Aplicacion instancia;
    private ArrayList<Caso> listaCasos;
    private SQLiteDatabase db;
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

        db = admin.getWritableDatabase();
        Caso retorno = new Caso();

        Cursor caso = db.rawQuery(
                "SELECT "+ DatabasePAConstantes.EntryCasos._ID + ", " +
                DatabasePAConstantes.EntryCasos.NOMBRE + ", " +
                DatabasePAConstantes.EntryCasos.PROCEDIMIENTO + ", " +
                DatabasePAConstantes.EntryCasos.PALABRAS_CLAVES_BUSQUEDA + ", " +
                DatabasePAConstantes.EntryCasos.ARCHIVO_AUDIO +" FROM " +
                DatabasePAConstantes.EntryCasos.TABLA_CASOS+" WHERE " +
                DatabasePAConstantes.EntryCasos.NOMBRE + " LIKE '%" + nombre +"%' ", null);

        caso.moveToFirst();
        retorno.setId(caso.getInt(0));
        retorno.setNombre(caso.getString(1));
        retorno.asignarProcedimiento(caso.getString(2));
        retorno.agregarPalabraClave(caso.getString(3));
        retorno.asignarAudioAProcedimiento(caso.getString(4));

        return retorno;
    }

//------------------------------------------------------------------------------------

//------------------- Obtener casos por palabras clave -------------------------------
    public ArrayList<String> getNombresCasos(String palabrasClave){
        db = admin.getWritableDatabase();
        ArrayList<String> nombreCasos = new ArrayList<String>();

        Cursor listaDeCasos = db.rawQuery(
                "SELECT "+ DatabasePAConstantes.EntryCasos.NOMBRE + " FROM " +
                DatabasePAConstantes.EntryCasos.TABLA_CASOS + " WHERE " +
                DatabasePAConstantes.EntryCasos.PALABRAS_CLAVES_BUSQUEDA +
                " LIKE '%" + palabrasClave.toLowerCase() + "%'", null);

        listaDeCasos.moveToFirst();

        while(!listaDeCasos.isAfterLast()){
            String nombre  = listaDeCasos.getString(listaDeCasos.getColumnIndex(DatabasePAConstantes.EntryCasos.NOMBRE));
            nombreCasos.add(nombre);//Se agrega el nombre almacenado en la
                                                                                        //colummna nombre
            listaDeCasos.moveToNext();
        }

        listaDeCasos.close();
        return nombreCasos;
    }

    public String agregarNuevoCaso(String nombreCaso){
        return null;
    }

    public String eliminarCaso(String nombreCaso){ return null; }

}
