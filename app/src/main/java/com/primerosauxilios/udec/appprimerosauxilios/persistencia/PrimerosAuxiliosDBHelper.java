package com.primerosauxilios.udec.appprimerosauxilios.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by daniel on 3/10/17.
 */

public class PrimerosAuxiliosDBHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private SQLiteDatabase mDataBase;
    private final Context mcontext;
    private boolean mNeedUpdate = false;

    public PrimerosAuxiliosDBHelper(Context context) {
        super (context, DatabasePA.DATABASE_NAME, null, DatabasePA.DATABASE_VERSION);
        if (Build.VERSION.SDK_INT >= 17)//Si el SDK es igual o mayor que la version 17
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException{
        if (mNeedUpdate){
            File dbFile = new File(DB_PATH + DatabasePA.DATABASE_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase(){ //Si la base de datos existe
        File dbFile = new File(DB_PATH + DatabasePA.DATABASE_NAME);
        return dbFile.exists();
    }

    private void copyDataBase(){
        if (!checkDataBase()){
            this.getReadableDatabase();
            this.close();
            try{
                copyDBFile();
            } catch (IOException mIOException){
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException{
        InputStream mInput = con
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

                                                    "'MedidasGenerales.mp4')");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Codigo para actualizar la base de datos
    }

}
