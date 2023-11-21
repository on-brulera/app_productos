package com.example.ramirezproductos;

public class Producto {

    public int Id;
    public String nombre;
    public String descripcion;
    public double stock;
    public double precio_unitario;
    public double tasa_iva;

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, double stock, double precio_unitario, double tasa_iva) {
        Id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio_unitario = precio_unitario;
        this.tasa_iva = tasa_iva;
    }
}
