package com.example.egalbornoz.pedidosapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Lista_Productos extends AppCompatActivity {
    private TextView tvCliente, tvRif, tvDireccion;
    private Clientes clientes;
    private TipoIdentificaciones tipoIdentificaciones;
    String server, database, user, pass;
    Conectar conectar;
    ArrayList<Productos> listaProductos = new ArrayList<Productos>();
    private RecyclerView recyclerViewProducto;
    private RecyclerViewProducto adaptadorProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__productos);
        clientes = (Clientes) getIntent().getSerializableExtra("data");
        tipoIdentificaciones = (TipoIdentificaciones) getIntent().getSerializableExtra("data1");
        tvCliente = (TextView) findViewById(R.id.tvClientes);
        tvRif = (TextView) findViewById(R.id.tvRif);
        tvDireccion = (TextView) findViewById(R.id.tvDireccion);
        //Receibe parametros del oro activity
        tvCliente.setText(clientes.getNombComercial());
        tvRif.setText(tipoIdentificaciones.getCodigo() + "-" + clientes.getNumeroIdentificacion());
        tvDireccion.setText(clientes.getDirComercial());
     /*--------------------------------------------------------------------------------------------------
        Método para Relación de Controles
     --------------------------------------------------------------------------------------------------*/
        recyclerViewProducto = (RecyclerView) findViewById(R.id.recyclerProducto);
        CargarConfiguracion();
        Conexion();
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(this));
      /*--------------------------------------------------------------------------------------------------
        Validó si la listaProductos esta vacia para Cargarla
     --------------------------------------------------------------------------------------------------*/
        Consultar_Producto();
        adaptadorProducto = new RecyclerViewProducto(listaProductos);
        recyclerViewProducto.setAdapter(adaptadorProducto);
    }

    /*--------------------------------------------------------------------------------------------------
               Método para cargar la configuración de la conexión
         --------------------------------------------------------------------------------------------------*/
    public void CargarConfiguracion() {
        SharedPreferences preferencias = getSharedPreferences("configuracion", Context.MODE_PRIVATE);
        server = preferencias.getString("servidor", "");
        database = preferencias.getString("basedatos", "");
        user = preferencias.getString("usuario", "");
        pass = preferencias.getString("contrasena", "");
    }

    /*--------------------------------------------------------------------------------------------------
      Método para Obtener lista de productos
   --------------------------------------------------------------------------------------------------*/
    public List<Productos> obtenerProductos() {
        List<Productos> productos = new ArrayList<>();
        return productos;
    }

    /*--------------------------------------------------------------------------------------------------
        Método para Consultar Productos
     --------------------------------------------------------------------------------------------------*/
    public void Consultar_Producto() {

        try {
            Statement stm = conectar.ConexionDB(server, database, user, pass).createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Productos");
            if (rs.next()) {
                do {
                    int c0 = rs.getInt(1);
                    String c1 = rs.getString(7);
                    String c2 = rs.getString(8);
                    Float c3 = rs.getFloat(9);
                    int c4 = 10;
                    listaProductos.add(new Productos(c0, c1, c2, c3, c4));
                } while (rs.next());
            } else {
                Toast toast2 = Toast.makeText(getApplicationContext(), "No se encontraron Registros", Toast.LENGTH_SHORT);
                toast2.show();
            }
            stm.close();
            rs.close();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /*--------------------------------------------------------------------------------------------------
      Método para establecer la conexión
     --------------------------------------------------------------------------------------------------*/
    private void Conexion() {
        conectar = new Conectar();
        try {
            Connection conexionbd = conectar.ConexionDB(server, database, user, pass);
            if (conexionbd == null) {
                Toast.makeText(getBaseContext(), "No se pudo establecer la conexion", Toast.LENGTH_SHORT).show();
                //} else {
                //    Sesion();
            }
        } catch (Exception e) {
            Log.e("ERROR - ", e.getMessage());
        }
    }
}