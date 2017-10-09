package com.primerosauxilios.udec.appprimerosauxilios.persistencia;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by daniel on 3/10/17.
 */

public class PrimerosAuxiliosDBHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public PrimerosAuxiliosDBHelper(Context context) {
        super (context, DatabasePAConstantes.DATABASE_NAME, null, DatabasePAConstantes.DATABASE_VERSION);
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
            File dbFile = new File(DB_PATH + DatabasePAConstantes.DATABASE_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase(){ //Si la base de datos existe
        File dbFile = new File(DB_PATH + DatabasePAConstantes.DATABASE_NAME);
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

    private void copyDBFile() throws IOException{//En este metodo, se copia la base de datos de assets a un archivo en la carpeta
                                                //data del emulador
        InputStream mInput = mContext.getAssets().open(DatabasePAConstantes.DATABASE_NAME);
        //Si la base de datos esta almacenada en la carpeta raw
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH+ DatabasePAConstantes.DATABASE_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mOutput.close();
    }

    public boolean openDataBase() throws SQLException{
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DatabasePAConstantes.DATABASE_NAME, null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //Codigo para actualizar la base de datos
        if(newVersion > oldVersion){
            mNeedUpdate = true;
        }
    }

}
