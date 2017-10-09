package com.primerosauxilios.udec.appprimerosauxilios.persistencia;


import android.provider.BaseColumns;

/**
 * Created by daniel on 28/09/17.
 */
public class DatabasePAConstantes {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DatabasePA";
    public static final String CASO_MEDIDAS_GENERALES="Medidas Generales de Primeros Auxilios";
    public static final String CASO="caso";

    public static abstract class EntryCasos implements BaseColumns{
        //La interface BaseColumns contiene un atributo int llamado _ID, que servira como clave primaria de la tabla
        //Casos
        public static final String TABLA_CASOS = "Casos";
        public static final String NOMBRE = "nombre";
        public static final String PROCEDIMIENTO = "procedimiento";
        public static final String PALABRAS_CLAVES_BUSQUEDA = "clavesBusqueda";
        public static final String ARCHIVO_AUDIO="archivoAudio";
      }
}
