package com.example.egalbornoz.pedidosapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.egalbornoz.pedidosapp.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    private EditText txtUser, txtPass;
    private Button btnLogin;
    private Conectar conectar;
    private String server, database, user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        //Asociar Controles
        txtUser = (EditText) findViewById(R.id.txtUsuario);
        txtPass = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        CargarConfiguracion();
     /*-------------------------------------------------------------------------------------------
       Métodos para el boton Login
     *-------------------------------------------------------------------------------------------*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conectar = new Conectar();
                try {
                    Connection conexionbd = conectar.ConexionDB(server, database, user, pass);
                    if (conexionbd == null) {
                        Toast.makeText(getBaseContext(), "No se pudo establecer la conexion", Toast.LENGTH_SHORT).show();
                    } else {
                        Sesion();
                    }
                } catch (Exception e) {
                    Log.e("ERROR - ", e.getMessage());
                }
            }
        });
    }

    /*--------------------------------------------------------------------------------------------------
        Carga parámetros de configuración del Servidor
        -----------------------------------------------------------------------------------------------*/
    private void CargarConfiguracion() {
        SharedPreferences preferencias = getSharedPreferences("configuracion", Context.MODE_PRIVATE);
        server = preferencias.getString("servidor", "");
        database = preferencias.getString("basedatos", "");
        user = preferencias.getString("usuario", "");
        pass = preferencias.getString("contrasena", "");
    }

    //Método para validar e iniciar sesión
    public void Sesion() {
        try {
            Statement stm = conectar.ConexionDB(server, database, user, pass).createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Usuarios WHERE Usuario ='" + txtUser.getText().toString() + "'");
            if (rs.next()) {
                String res = rs.getString(7).toString();
                Intent intencion = new Intent(getApplicationContext(),Lista_Clientes.class);
                startActivity(intencion);
                finish();
            } else {
                Toast toast2 = Toast.makeText(getApplicationContext(), "Usuario o Pass Inválido", Toast.LENGTH_SHORT);
                toast2.show();
            }
            stm.close();
            rs.close();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    /*-------------------------------------------------------------------------------------------
      Método para Mostrar/Ocultar el menu
    *-------------------------------------------------------------------------------------------*/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    /*-------------------------------------------------------------------------------------------
      Método para funcionalidad de las opciones
    *-------------------------------------------------------------------------------------------*/

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.item1) {
            Intent intencion = new Intent(getApplicationContext(), Configuracion.class);
            startActivity(intencion);
        }
        return super.onOptionsItemSelected(item);
    }
}

