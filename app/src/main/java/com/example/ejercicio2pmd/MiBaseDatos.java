package com.example.ejercicio2pmd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MiBaseDatos extends SQLiteOpenHelper {

    private String sqlCreate = "CREATE TABLE contacto(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellidos TEXT, telefono TEXT, fechaNacimiento TEXT)";

    public MiBaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS contacto");
        sQLiteDatabase.execSQL(sqlCreate);
    }
}
