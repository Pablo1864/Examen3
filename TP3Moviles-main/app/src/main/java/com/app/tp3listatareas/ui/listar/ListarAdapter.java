package com.app.tp3listatareas.ui.listar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.tp3listatareas.Modelo.Auto;
import com.app.tp3listatareas.R;

import java.util.List;

public class ListarAdapter extends RecyclerView.Adapter<ListarAdapter.ViewHolderListar> {

    private List<Auto> listaAutos;
    private LayoutInflater layoutInflater;

    public ListarAdapter(List<Auto> listaAutos, LayoutInflater layoutInflater) {
        this.listaAutos = listaAutos;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolderListar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_lista_autos, parent, false);
        return new ViewHolderListar(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListar holder, int position) {
        Auto auto = listaAutos.get(position);
        holder.tvPatente.setText(auto.getPatente());
        holder.tvMarca.setText(auto.getMarca());
        holder.tvModelo.setText(auto.getModelo());
        holder.tvPrecio.setText(auto.getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaAutos.size();
    }

    public class ViewHolderListar extends RecyclerView.ViewHolder {

        TextView tvPatente, tvMarca, tvModelo, tvPrecio;

        public ViewHolderListar(@NonNull View itemView) {
            super(itemView);
            tvPatente = itemView.findViewById(R.id.editPatente);
            tvMarca = itemView.findViewById(R.id.editMarca);
            tvModelo = itemView.findViewById(R.id.editModelo);
            tvPrecio = itemView.findViewById(R.id.editPrecio);
        }
    }
}


