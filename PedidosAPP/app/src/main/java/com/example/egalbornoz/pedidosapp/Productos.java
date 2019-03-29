package com.example.egalbornoz.pedidosapp;

import java.io.Serializable;
import java.net.IDN;

public class Productos implements Serializable {
    private int IdProducto;
    private String Nombre;
    private String Presentacion;
    private float Precio;
    private int Cant_Solicitada;

    public Productos() {
    }


    public Productos(int idproducto,String nombre, String presentacion, float precio,int cant_Solicitada) {
        IdProducto=idproducto;
        Nombre = nombre;
        Presentacion = presentacion;
        Precio = precio;
        Cant_Solicitada = cant_Solicitada;

    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {IdProducto = idProducto;}

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPresentacion() {
        return Presentacion;
    }

    public void setPresentacion(String presentacion) {
        Presentacion = presentacion;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    public int getCant_Solicitada() {
        return Cant_Solicitada;
    }

    public void setCant_Solicitada(int cant_Solicitada) {Cant_Solicitada = cant_Solicitada;}

    @Override
    public String toString() {
        return Nombre;
    }
}
