package com.example.zhelon.instagramcseapplication.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhelon.instagramcseapplication.R;
import com.example.zhelon.instagramcseapplication.pojo.Photo;

import java.util.List;

/**
 * Created by zhelon on 23-11-17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    private List<Photo> items;

    public PhotoAdapter(List<Photo> items){
        this.items = items;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView comment;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            comment = (TextView) itemView.findViewById(R.id.comment);

        }
    }

    @Override
    public PhotoAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.photo_adapter, viewGroup, false);
        return new PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.PhotoViewHolder holder, int position) {

        holder.comment.setText(items.get(position).getComment());
        holder.image.setImageBitmap(toBitmap(items.get(position).getImage()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private Bitmap toBitmap(byte[] foto) {

        Bitmap bmp = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        return Bitmap.createScaledBitmap(bmp, 100, 150, false);

    }


}
