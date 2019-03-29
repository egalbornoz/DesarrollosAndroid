package com.example.egalbornoz.pedidosapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Lista_Clientes extends AppCompatActivity {
    String server, database, user, pass, nombre;
    int idSeleccionado;
    Spinner spZonas;
    Conectar conectar;
    ArrayList<Zonas> listaZonas = new ArrayList<Zonas>();
    ArrayList<Clientes> listaCliente = new ArrayList<Clientes>();
    private Clientes itemClientes;
    ArrayList<TipoIdentificaciones> listaIdentificacion = new ArrayList<TipoIdentificaciones>();
    private RecyclerView recyclerViewCliente;
    private RecyclerViewClientes adaptadorCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
     /*--------------------------------------------------------------------------------------------------
        Método para Relación de Controles
     --------------------------------------------------------------------------------------------------*/
        spZonas = (Spinner) findViewById(R.id.spZonas);
        recyclerViewCliente = (RecyclerView) findViewById(R.id.recyclerCliente);
        CargarConfiguracion();
        Conexion();
        CargarZonas();
        final ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaZonas);
        spZonas.setAdapter(adapter);
        recyclerViewCliente.setLayoutManager(new LinearLayoutManager(this));
    /*--------------------------------------------------------------------------------------------------
        Método para recuperar los valores del spinner
     --------------------------------------------------------------------------------------------------*/
        spZonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, long id) {
                Object item = parent.getItemAtPosition(position);
                idSeleccionado = ((Zonas) item).getIdZona();
                nombre = ((Zonas) item).getNombre();
                //Toast.makeText(getBaseContext(), nombre, Toast.LENGTH_SHORT).show();
                listaCliente.clear();
                Consultar_Cliente();
                adaptadorCliente = new RecyclerViewClientes(listaCliente, listaIdentificacion);
                recyclerViewCliente.setAdapter(adaptadorCliente);
                adaptadorCliente.setOnClickListener(new View.OnClickListener() {
                    @Override                   //Evento Click
                    public void onClick(View v) {
                        //Captura el valor de la posicion seleccionada;
                        //String a = listaCliente.get(recyclerViewCliente.getChildAdapterPosition(v)).getNombComercial();
                        //Enviar datos a  otro activity
                        Intent intent = new Intent(Lista_Clientes.this, Lista_Productos.class);
                        //preparamos los datos a enviar
                        intent.putExtra("data", (listaCliente.get(recyclerViewCliente.getChildAdapterPosition(v))));
                        intent.putExtra("data1", (listaIdentificacion.get(recyclerViewCliente.getChildAdapterPosition(v))));
                        startActivity(intent);
                        //Toast.makeText(getApplicationContext(), "Selección: "+ a, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
        Método para Obtener lista de clientes
     --------------------------------------------------------------------------------------------------*/
    public List<Clientes> obtenerClientes() {
        List<Clientes> clientes = new ArrayList<>();
        return clientes;
    }

    /*--------------------------------------------------------------------------------------------------
      Método que obtiene la ista de tipo de Identificación
     --------------------------------------------------------------------------------------------------*/
    public List<TipoIdentificaciones> obtenerIdentificacion() {
        List<TipoIdentificaciones> identificacion = new ArrayList<>();
        return identificacion;
    }

    /*--------------------------------------------------------------------------------------------------
        Método para cargar las Zonas
     --------------------------------------------------------------------------------------------------*/
    private void CargarZonas() {
        try {
            Statement stm = conectar.ConexionDB(server, database, user, pass).createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Zonas");
            if (rs.next()) {
                do {
                    int Z1 = rs.getInt(1);
                    String Z2 = rs.getString(2);
                    listaZonas.add(new Zonas(Z1, Z2));
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
        Método para Consultar Clientes
     --------------------------------------------------------------------------------------------------*/
    public void Consultar_Cliente() {

        try {
            Statement stm = conectar.ConexionDB(server, database, user, pass).createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Clientes " +
                    "INNER JOIN TipoIdentificaciones ON Clientes.IdTipoIdentificacion=TipoIdentificaciones.IdTipoIdentificacion" +
                    " WHERE Clientes.IdTipoIdentificacion=TipoIdentificaciones.IdTipoIdentificacion AND Clientes.IdZona=" +
                    "'" + idSeleccionado + "'");
            if (rs.next()) {
                do {
                    int c1 = rs.getInt(1);//IdCliente
                    String c2 = rs.getString(7);//Numero Identificacion
                    String c3 = rs.getString(8);//Razon Social
                    String c4 = rs.getString(10);//Direccion Comerc ial
                    int c5 = rs.getInt(29);//Id TipoIdentificacion
                    String c6 = rs.getString(30);//Código

                    listaCliente.add(new Clientes(c1, c2, c3, c4));
                    listaIdentificacion.add(new TipoIdentificaciones(c5, c6));
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