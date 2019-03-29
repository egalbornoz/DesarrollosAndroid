package com.example.egalbornoz.pedidosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Detalles_Producto extends AppCompatActivity {
    ArrayList<Productos> listaProductos = new ArrayList<Productos>();
    private RecyclerView recyclerViewProducto;
    private RecyclerView recyclerViewCliente;
    ArrayList<Clientes> listaCliente = new ArrayList<Clientes>();
    ArrayList<TipoIdentificaciones> listaIdentificacion = new ArrayList<TipoIdentificaciones>();
    TextView txtProducto, txtPrecio, txtDisponible, txtSubtotal;
    Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles__producto);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        txtPrecio = (TextView) findViewById(R.id.txtPrecio);
        txtProducto = (TextView) findViewById(R.id.txtLabel);
        txtDisponible = (TextView) findViewById(R.id.txtDisponible);
        txtSubtotal = (TextView) findViewById(R.id.txtSubTotal);

        final Productos productos = (Productos) getIntent().getExtras().getSerializable("data");
        txtProducto.setText(productos.getNombre());
        txtPrecio.setText("Precio :" + String.valueOf(productos.getPrecio()));
        txtDisponible.setText("Id :" + String.valueOf(productos.getIdProducto()));
        Toast.makeText(getApplicationContext(), "ggg", Toast.LENGTH_SHORT).show();


        //Evento Click al Boton Aceptar
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaProductos.size()>0){

                    Toast.makeText(getApplicationContext(),"LLENO",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"VACIO",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }


}
