package com.primerosauxilios.udec.appprimerosauxilios.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by daniel on 3/10/17.
 */

public class PrimerosAuxiliosDBHelper extends SQLiteOpenHelper {

    public PrimerosAuxiliosDBHelper(Context context) {
        super (context, MyDatabase.DATABASE_NAME, null, MyDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Tabla Casos
        /*sqLiteDatabase.execSQL("CREATE TABLE " + MyDatabase.EntryCasos.TABLA_CASOS +
                                " (" + MyDatabase.EntryCasos._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                MyDatabase.EntryCasos.NOMBRE + " TEXT NOT NULL, " +
                                MyDatabase.EntryCasos.PROCEDIMIENTO + " TEXT NOT NULL, " +
                                MyDatabase.EntryCasos.PALABRAS_CLAVES_BUSQUEDA + " TEXT NOT NULL, " +
                                MyDatabase.EntryCasos.ARCHIVO_AUDIO + " TEXT NOT NULL" + ")");*/

        //Codigo para llenar la base de datos con casos
        /*sqLiteDatabase.execSQL("INSERT INTO "+MyDatabase.EntryCasos.TABLA_CASOS+" VALUES (null, " +
                                                        "'<h1>Medidas Generales de Primeros Auxilios</h1><br><br>'," +
                                                        "'<b>Estar tranquilos, pero actuar rápidamente</b>: Los primeros " +
                                                        "instantes después de ocurrida la emergencia son cruciales. " +
                                                        "Se debe mantener la calma para poder dar las disposiciones correctas, " +
                                                        "y realizar un traslado rápido y sin complicaciones innecesarias para el " +
                                                        "afectado.<br>" +
                                                        " <br>" +
                                                        "<b>Examinar la escena de la emergencia</b>: Verificar que no hayan más víctimas," +
                                                        " sobretodo que no revistan una mayor gravedad, y por tanto prioridad en la" +
                                                        " atención.  También se debe verificar que las fuentes de peligro ya no sigan" +
                                                        " representando una amenaza: cables energizados, fugas de gas o compuestos químicos," +
                                                        " fuego, etc.<br>" +
                                                        " <br>" +
                                                        "<b>No mover al afectado de manera innecesaria</b>: Como norma básica, jamás debe moverse de" +
                                                        " inmediato a un accidentado sin primero revisar su estado y tomar las medidas " +
                                                        "preliminares. Un herido grave no debe ser movilizado, excepto por alguna de las" +
                                                        " siguientes razones:<br>" +
                                                        " <br>" +
                                                        "a. Aplicar los primeros auxilios.<br>" +
                                                        "b. Evitar el agravamiento de heridas.<br>" +
                                                        "c. Proteger a la víctima de un nuevo accidente. <br>" +
                                                        " <br>" +
                                                        "<b>Revisar al herido</b>: Verificar los signos vitales como la respiración y el pulso. " +
                                                        "Revisar el estado de consciencia, si está sangrando, si está fracturado o si tiene " +
                                                        "quemaduras. Cerciorarse de que todo haya sido revisado.<br>" +
                                                        " <br>" +
                                                        "<b>Hacer solo lo indispensable</b>: Hacer lo necesario, con la finalidad de no generar retrasos" +
                                                        " innecesarios en el traslado de la víctima. El que presta los primeros auxilios no tiene " +
                                                        "como finalidad reemplazar la atención del personal especializado. Simplemente se encarga de " +
                                                        "facilitar la labor posterior de los paramédicos y médicos. No vestir al incidentado, " +
                                                        "sino que por el contrario asegurar de que esté lo más libre y despejado posible de ataduras " +
                                                        "innecesarias.<br>" +
                                                        " <br>" +
                                                        "<b<Mantener al herido caliente</b>: Si hace frío, se debe procurar que el cuerpo de la " +
                                                        "víctima" +
                                                        " esté debidamente abrigado. No obstante, no se debe permitir que el afectado esté " +
                                                        "caliente en exceso. </br>" +
                                                        " <br>" +
                                                        "<b>No dar nunca de beber a una persona lesionada</b>: Se puede dar el caso de que la persona" +
                                                        " no puede ingerir el líquido correctamente. y este penetre en sus vías de respiración, " +
                                                        "causándole ahogamiento. Si la víctima está consciente y no tiene herida profunda en el " +
                                                        "abdomen, se le puede dar de beber lentamente a pequeños sorbos. Es preferible darle café" +
                                                        " o té caliente si hace frío, en vez de bebidas alcohólicas.\n" +
                                                        "<br>" +
                                                        "<b>Tranquilizar a la víctima</b>:Debido a que la víctima tiene miedo, se le debe hablar. " +
                                                        "Su vida ha sido truncada de forma brusca y está sufriendo por sus acompañantes o su familia." +
                                                        " Se le debe tranquilizar, calmar sus temores y levantar el ánimo. Se le debe decir que " +
                                                        "hay gente ocupándose de ella.', " +
                                                        "'Medidas Generales de Primeros Auxilios'," +
                                                        "'MedidasGenerales.mp4')");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Codigo para actualizar la base de datos
    }

}
