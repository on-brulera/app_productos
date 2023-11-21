package com.example.ramirezproductos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQL extends SQLiteOpenHelper {
    public AdminSQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS  productos (id int PRIMARY KEY, nombre TEXT(50), descripcion TEXT(200), stock NUMBER(10,3), precio_unitario NUMBER(10,3),tasa_iva NUMBER(10,3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
