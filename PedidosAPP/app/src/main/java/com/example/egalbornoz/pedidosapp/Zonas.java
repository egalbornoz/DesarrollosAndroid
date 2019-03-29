package com.example.egalbornoz.pedidosapp;

public class Zonas {
    private int IdZona;
    private  String Nombre;

    public Zonas() {
    }

    public Zonas(int idZona, String nombre) {
        IdZona = idZona;
        Nombre = nombre;
    }

    public int getIdZona() {
        return IdZona;
    }

    public void setIdZona(int idZona) {
        IdZona = idZona;
    }

    public String getNombre() {
        return Nombre;
    }

    @Override
    public String toString() {
        return IdZona +"  " + Nombre ;
    }
}
