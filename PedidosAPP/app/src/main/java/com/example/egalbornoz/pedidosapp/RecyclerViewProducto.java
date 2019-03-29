package com.example.egalbornoz.pedidosapp;

import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
//Clase que Sirve de puente entre la fuente de informacion y la presentacion

public class RecyclerViewProducto extends RecyclerView.Adapter<RecyclerViewProducto.ViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private AdapterView.OnItemClickListener listener1;



    public void setOnClickListener1(View.OnClickListener listener)
    {

        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView producto, presentacion, precio, subtotal;
        private EditText requerida;
        public Button btnadd, btnremove;

        public ViewHolder(View itemView) {
            super(itemView);
            producto = (TextView) itemView.findViewById(R.id.tvProducto);
            presentacion = (TextView) itemView.findViewById(R.id.tvPresentacion);
            precio = (TextView) itemView.findViewById(R.id.tvPrecio);
            requerida = (EditText) itemView.findViewById(R.id.etRequeridas);
            subtotal = (TextView) itemView.findViewById(R.id.txtSubTotal);
            btnadd = (Button) itemView.findViewById(R.id.btn_add);
            btnremove = (Button) itemView.findViewById(R.id.btn_remove);
        }

    }

    public List<Productos> listaProductos;

    public RecyclerViewProducto(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //SE ASIGNAN LOS VALORES DE LA LISTA AL RECICLER VIEW
        holder.producto.setText(listaProductos.get(position).getNombre());
        holder.presentacion.setText(listaProductos.get(position).getPresentacion());
        holder.requerida.setText(String.valueOf(listaProductos.get(position).getCant_Solicitada()));
        holder.precio.setText("Precio: " + String.valueOf(listaProductos.get(position).getPrecio()));
    }

    public int getItemCount() {
        return listaProductos.size();

    }
}