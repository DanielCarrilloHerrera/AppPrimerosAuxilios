package com.primerosauxilios.udec.appprimerosauxilios.logica;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.PrimerosAuxiliosDBHelper;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by daniel on 18/11/17.
 */
public class AplicacionPU {

    private static AplicacionPU instancia;
    private PrimerosAuxiliosDBHelper admin;
    private Context context;
    private SQLiteDatabase db;
    private ArrayList<Caso> listaCasos = new ArrayList();

    public AplicacionPU(){

    }
    @Test
    public void getInstancia() throws Exception {
        if (instancia == null) {
            instancia = new AplicacionPU();
        }
    }

    @Test
    public void getListaCasos() throws Exception {
    }

    @Test
    public void getCaso() throws Exception {
        this.db = this.admin.getWritableDatabase();
        String nombre="";
        Caso retorno = new Caso();
        Cursor caso = this.db.rawQuery("SELECT _id, " +
                                        "nombre, " +
                                        "procedimiento, " +
                                        "clavesBusqueda, " +
                                        "archivoAudio " +
                                        "FROM Casos " +
                                        "WHERE nombre " +
                                        "LIKE '%" + nombre + "%' ", null);
        caso.moveToFirst();
        retorno.setId(caso.getInt(0));
        retorno.setNombre(caso.getString(1));
        retorno.asignarProcedimiento(caso.getString(2));
        retorno.agregarPalabraClave(caso.getString(3));
        retorno.asignarAudioAProcedimiento(caso.getString(4));
    }


    @Test
    public void getNombresCasos() throws Exception {
        this.db = this.admin.getWritableDatabase();
        String palabrasClave = "";
        ArrayList<String> nombreCasos = new ArrayList();
        Cursor listaDeCasos = this.db.rawQuery("SELECT nombre " +
                                                "FROM Casos " +
                                                "WHERE clavesBusqueda " +
                                                "LIKE '%" +
                                                palabrasClave.toLowerCase() + "%'", null);
        listaDeCasos.moveToFirst();
        while (!listaDeCasos.isAfterLast()) {
            nombreCasos.add(listaDeCasos.getString(listaDeCasos.getColumnIndex(DatabasePAConstantes.EntryCasos.NOMBRE)));
            listaDeCasos.moveToNext();
        }
        listaDeCasos.close();
    }

    @Test
    public void agregarNuevoCaso() throws Exception {
    }

    @Test
    public void eliminarCaso() throws Exception {
    }

}