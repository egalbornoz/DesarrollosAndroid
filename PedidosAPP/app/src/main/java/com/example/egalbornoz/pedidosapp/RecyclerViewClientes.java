package com.example.egalbornoz.pedidosapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;
//Clase que Sirve de puente entre la fuente de informacion y la presentacion

public class RecyclerViewClientes extends RecyclerView.Adapter<RecyclerViewClientes.ViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private AdapterView.OnItemClickListener listener1;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cliente, tipoIdentificacion, direccion;

        public ViewHolder(View itemView) {
            super(itemView);
            cliente = (TextView) itemView.findViewById(R.id.tvCliente);
            tipoIdentificacion = (TextView) itemView.findViewById(R.id.tvRif);
            direccion = (TextView) itemView.findViewById(R.id.tvDireccion);
        }
    }

    public List<Clientes> listaClientes;
    public List<TipoIdentificaciones> listaTipoIdentificacion;

    public RecyclerViewClientes(List<Clientes> listaClientes,
                                List<TipoIdentificaciones> listaTipoIdentificacion) {
        this.listaClientes = listaClientes;
        this.listaTipoIdentificacion = listaTipoIdentificacion;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cliente_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cliente.setText(listaClientes.get(position).getNombComercial());
        holder.tipoIdentificacion.setText(listaTipoIdentificacion.get(position)
                .getCodigo() + "-" + listaClientes.get(position).getNumeroIdentificacion());
        holder.direccion.setText(listaClientes.get(position).getDirComercial());
    }

    public int getItemCount() {
        return listaClientes.size();

    }
}