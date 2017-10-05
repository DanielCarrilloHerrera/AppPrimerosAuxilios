package com.primerosauxilios.udec.appprimerosauxilios.persistencia;


import android.provider.BaseColumns;

/**
 * Created by daniel on 28/09/17.
 */
public class MyDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyDataBase";

    //La interface BaseColumns ya contiene un atributo int llamado _ID
    //Se tomara ese atributo como llave primaria para la tabla CASOS
    public static abstract class EntryCasos implements BaseColumns{
        public static final String TABLA_CASOS = "Casos";
        public static final String NOMBRE = "nombre";
        public static final String PROCEDIMIENTO = "procedimiento";
        public static final String PALABRAS_CLAVES_BUSQUEDA = "clavesBusqueda";
        public static final String ARCHIVO_AUDIO="archivoAudio";
    }


}
