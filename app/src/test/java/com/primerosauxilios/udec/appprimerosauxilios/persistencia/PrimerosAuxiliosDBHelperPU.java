package com.primerosauxilios.udec.appprimerosauxilios.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by daniel on 19/11/17.
 */
public class PrimerosAuxiliosDBHelperPU {
    private static String DB_PATH = "";
    private Context mContext;
    private SQLiteDatabase mDataBase;
    private boolean mNeedUpdate = false;
    int oldVersion, newVersion;

    @Test
    public void updateDataBase() throws Exception {
        if (this.mNeedUpdate) {
            File dbFile = new File(DB_PATH + DatabasePAConstantes.DATABASE_NAME);
            if (dbFile.exists()) {
                dbFile.delete();
            }
            copyDataBase();
            this.mNeedUpdate = false;
        }
    }

    @Test
    public void copyDataBase() {
        if (!checkDataBase()) {
            try {
                close();
            } catch (Exception e) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        return new File(DB_PATH + DatabasePAConstantes.DATABASE_NAME).exists();
    }

    @Test
    public void openDataBase() throws Exception {
        this.mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DatabasePAConstantes.DATABASE_NAME, null, 268435456);
    }

    @Test
    public void close() throws Exception {
        if (this.mDataBase != null) {
            this.mDataBase.close();
        }
    }

    @Test
    public void onCreate() throws Exception {
    }

    @Test
    public void onUpgrade() throws Exception {
        if (newVersion > oldVersion) {
            this.mNeedUpdate = true;
        }
    }

}