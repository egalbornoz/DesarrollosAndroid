package com.example.egalbornoz.pedidosapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.egalbornoz.pedidosapp.R.id.btnGuardar;

public class Configuracion extends AppCompatActivity {
    private EditText etServer, etDbase, etUser, etPass;
    private Button btnGuardar;
    private String server, database, user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        etServer = (EditText) findViewById(R.id.etIP);
        etDbase = (EditText) findViewById(R.id.etDB);
        etUser = (EditText) findViewById(R.id.etUS);
        etPass = (EditText) findViewById(R.id.etPA);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        CargarConfiguracio();
    }

    //Carga los parametros de configuración de Conexión
    private void CargarConfiguracio() {
        SharedPreferences preferencias = getSharedPreferences("configuracion", Context.MODE_PRIVATE);
        server = preferencias.getString("servidor", "");
        database = preferencias.getString("basedatos", "");
        user = preferencias.getString("usuario", "");
        pass = preferencias.getString("contrasena", "");
        etServer.setText(server);
        etDbase.setText(database);
        etUser.setText(user);
        etPass.setText(pass);
    }

    /*--------------------------------------------------------------------------------------------------
        Método para Guardar en un archivo
     --------------------------------------------------------------------------------------------------*/
    public void Guargdar(View view) {
        String ser = etServer.getText().toString();
        String db = etDbase.getText().toString();
        String us = etUser.getText().toString();
        String pa = etPass.getText().toString();
        //Se crea el archivo que contendra los datos
        SharedPreferences preferencias = getSharedPreferences("configuracion", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        //Se asignan los datos a guardar
        obj_editor.putString("servidor", ser);
        obj_editor.putString("basedatos", db);
        obj_editor.putString("usuario", us);
        obj_editor.putString("contrasena", pa);
        obj_editor.commit();
        Toast.makeText(getApplicationContext(), "Configuración Guardada", Toast.LENGTH_SHORT).show();
        finish();
    }
}
