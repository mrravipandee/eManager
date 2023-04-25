package com.mrravipande.emanager.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrravipande.emanager.R;

import java.util.List;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.imageViewAdapter> {

    private Context context;
    private List<String> images;

    public imageAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public imageViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.images_home_feed, parent, false);
        return new imageViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull imageViewAdapter holder, int position) {

        Glide.with(context).load(images.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class imageViewAdapter extends RecyclerView.ViewHolder {
        ImageView imageView;
        public imageViewAdapter(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_xml);
        }
    }
}
