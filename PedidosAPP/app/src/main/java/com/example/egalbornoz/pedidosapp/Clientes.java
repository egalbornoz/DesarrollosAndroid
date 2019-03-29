
package com.example.egalbornoz.pedidosapp;

import java.io.Serializable;

public class Clientes implements Serializable {
    private int IdCliente;
    private String NumeroIdentificacion;
    private String NombComercial;
    private String DirComercial;


    public Clientes() {
    }

    public Clientes(int idCliente, String numeroIdentificacion, String nombComercial, String dirComercial) {
        IdCliente = idCliente;
        NumeroIdentificacion = numeroIdentificacion;
        NombComercial = nombComercial;
        DirComercial = dirComercial;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getNumeroIdentificacion() {
        return NumeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        NumeroIdentificacion = numeroIdentificacion;
    }

    public String getNombComercial() {
        return NombComercial;
    }

    public void setNombComercial(String nombComercial) {
        NombComercial = nombComercial;
    }

    public String getDirComercial() {
        return DirComercial;
    }

    public void setDirComercial(String dirComercial) {
        DirComercial = dirComercial;
    }

    @Override
    public String toString() {
        return NombComercial;
    }
}

