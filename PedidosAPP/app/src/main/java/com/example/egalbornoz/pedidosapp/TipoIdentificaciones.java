package com.example.egalbornoz.pedidosapp;

import java.io.Serializable;

public class TipoIdentificaciones implements Serializable {
    private int IdTipoIdentificacion;
    private String Codigo;

    public TipoIdentificaciones() {
    }

    public TipoIdentificaciones(int idTipoIdentificacion, String codigo) {
        IdTipoIdentificacion = idTipoIdentificacion;
        Codigo = codigo;
    }

    public int getIdTipoIdentificacion() {
        return IdTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        IdTipoIdentificacion = idTipoIdentificacion;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    @Override
    public String toString() {
        return  Codigo;
    }
}
