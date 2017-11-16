package com.primerosauxilios.udec.appprimerosauxilios.logica;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.DatabasePAConstantes.EntryCasos;
import com.primerosauxilios.udec.appprimerosauxilios.persistencia.PrimerosAuxiliosDBHelper;
import java.util.ArrayList;

public class Aplicacion {
    private static Aplicacion instancia;
    private PrimerosAuxiliosDBHelper admin;
    private Context context;
    private SQLiteDatabase db;
    private ArrayList<Caso> listaCasos = new ArrayList();

    private Aplicacion(Context context) {
        this.context = context;
        this.admin = new PrimerosAuxiliosDBHelper(context);
    }

    public static Aplicacion getInstancia(Context context) {
        if (instancia == null) {
            instancia = new Aplicacion(context);
        }
        return instancia;
    }

    public ArrayList<Caso> getListaCasos() {
        return this.listaCasos;
    }

    public Caso getCaso(int id) {
        return null;
    }

    public Caso getCaso(String nombre) {
        this.db = this.admin.getWritableDatabase();
        Caso retorno = new Caso();
        Cursor caso = this.db.rawQuery("SELECT _id, nombre, procedimiento, clavesBusqueda, archivoAudio FROM Casos WHERE nombre LIKE '%" + nombre + "%' ", null);
        caso.moveToFirst();
        retorno.setId(caso.getInt(0));
        retorno.setNombre(caso.getString(1));
        retorno.asignarProcedimiento(caso.getString(2));
        retorno.agregarPalabraClave(caso.getString(3));
        retorno.asignarAudioAProcedimiento(caso.getString(4));
        return retorno;
    }

    public ArrayList<String> getNombresCasos(String palabrasClave) {
        this.db = this.admin.getWritableDatabase();
        ArrayList<String> nombreCasos = new ArrayList();
        Cursor listaDeCasos = this.db.rawQuery("SELECT nombre FROM Casos WHERE clavesBusqueda LIKE '%" + palabrasClave.toLowerCase() + "%'", null);
        listaDeCasos.moveToFirst();
        while (!listaDeCasos.isAfterLast()) {
            nombreCasos.add(listaDeCasos.getString(listaDeCasos.getColumnIndex(EntryCasos.NOMBRE)));
            listaDeCasos.moveToNext();
        }
        listaDeCasos.close();
        return nombreCasos;
    }

    public String agregarNuevoCaso(String nombreCaso) {
        return null;
    }

    public String eliminarCaso(String nombreCaso) {
        return null;
    }
}
