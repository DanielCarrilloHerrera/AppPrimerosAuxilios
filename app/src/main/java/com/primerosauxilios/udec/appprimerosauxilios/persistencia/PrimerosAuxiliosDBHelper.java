package com.primerosauxilios.udec.appprimerosauxilios.persistencia;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PrimerosAuxiliosDBHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "";
    private final Context mContext;
    private SQLiteDatabase mDataBase;
    private boolean mNeedUpdate = false;

    public PrimerosAuxiliosDBHelper(Context context) throws NullPointerException{
        super(context, DatabasePAConstantes.DATABASE_NAME, null, 1);
        if (VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
        copyDataBase();
        getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (this.mNeedUpdate) {
            File dbFile = new File(DB_PATH + DatabasePAConstantes.DATABASE_NAME);
            if (dbFile.exists()) {
                dbFile.delete();
            }
            copyDataBase();
            this.mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        return new File(DB_PATH + DatabasePAConstantes.DATABASE_NAME).exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            getReadableDatabase();
            close();
            try {
                copyDBFile();
            } catch (IOException e) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = this.mContext.getAssets().open(DatabasePAConstantes.DATABASE_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DatabasePAConstantes.DATABASE_NAME);
        byte[] mBuffer = new byte[1024];
        while (true) {
            int mLength = mInput.read(mBuffer);
            if (mLength > 0) {
                mOutput.write(mBuffer, 0, mLength);
            } else {
                mOutput.flush();
                mOutput.close();
                mOutput.close();
                return;
            }
        }
    }

    public boolean openDataBase() throws SQLException {
        this.mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DatabasePAConstantes.DATABASE_NAME, null, 268435456);
        return this.mDataBase != null;
    }

    public synchronized void close() {
        if (this.mDataBase != null) {
            this.mDataBase.close();
        }
        super.close();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            this.mNeedUpdate = true;
        }
    }
}
