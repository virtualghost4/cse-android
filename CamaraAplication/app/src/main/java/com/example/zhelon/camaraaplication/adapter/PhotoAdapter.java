package com.example.zhelon.camaraaplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhelon.camaraaplication.R;
import com.example.zhelon.camaraapplication.pojo.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<Photo> items;

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;

        public PhotoViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            visitas = (TextView) v.findViewById(R.id.visitas);
        }
    }

    public PhotoAdapter(List<Photo> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_photo_card, viewGroup, false);
        return new PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.visitas.setText("Visitas:"+String.valueOf(items.get(i).getVisitas()));
    }
}