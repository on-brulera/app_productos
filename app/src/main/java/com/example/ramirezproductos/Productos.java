package com.example.ramirezproductos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class Productos {

    private AdminSQL sql;

    public Productos (@Nullable Context context, @Nullable String name, int version){
        sql = new AdminSQL(context,name,null,version);
    }

    public Producto Create(int id, String nombre, String descripcion, double stock, double precio_unitario, double tasa_iva){
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();
        item.put("id",id);
        item.put("nombre",nombre);
        item.put("descripcion",descripcion);
        item.put("stock",stock);
        item.put("precio_unitario",precio_unitario);
        item.put("tasa_iva",tasa_iva);
        bdd.insert("productos",null,item);
        return new Producto(id,nombre,descripcion,stock,precio_unitario,tasa_iva);
    }

    public Producto Read_ById(int id){
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,descripcion,stock,precio_unitario,tasa_iva FROM productos where id = " + id,null);
        if (data.getCount()> 0){
            data.moveToFirst();
            return  new Producto(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getDouble(3),
                    data.getDouble(4),
                    data.getDouble(5)
                    );
        }
        return null;
    }

    public Producto[] Read_All(){
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,descripcion,stock,precio_unitario,tasa_iva FROM productos ORDER BY id",null);
        if (data.getCount()> 0){
            Producto[] productos = new Producto[data.getCount()];
            int indice = 0;
            while (data.moveToFirst()){
                productos[indice] = new Producto(
                        data.getInt(0),
                        data.getString(1),
                        data.getString(2),
                        data.getDouble(3),
                        data.getDouble(4),
                        data.getDouble(5)
                );
            }
            return productos;
        }
        return null;
    }

    public Producto[] Read_ByName(String nombre){
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,descripcion,stock,precio_unitario,tasa_iva FROM productos WHERE nombre LIKE '%" + nombre + "%' ORDER BY nombre",null);
        if (data.getCount()> 0){
            Producto[] productos = new Producto[data.getCount()];
            int indice = 0;
            while (data.moveToFirst()){
                productos[indice] = new Producto(
                        data.getInt(0),
                        data.getString(1),
                        data.getString(2),
                        data.getDouble(3),
                        data.getDouble(4),
                        data.getDouble(5)
                );
            }
            return productos;
        }
        return null;
    }

    public void Update(int id, String nombre, String descripcion, double stock, double precio_unitario, double tasa_iva){
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();
        item.put("id",id);
        item.put("nombre",nombre);
        item.put("descripcion",descripcion);
        item.put("stock",stock);
        item.put("precio_unitario",precio_unitario);
        item.put("tasa_iva",tasa_iva);
        bdd.update("productos", item,"id="+id,null);
    }

    public void Delete(int id){
        SQLiteDatabase bdd = sql.getWritableDatabase();
        bdd.delete("productos","id="+id,null);
    }
}
