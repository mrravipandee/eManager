package com.mrravipande.emanager.eBook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrravipande.emanager.R;

import java.util.List;

public class eBookAdapter extends RecyclerView.Adapter<eBookAdapter.EBookViewHolder> {

    private Context context;
    private List<eBookData> list;

    public eBookAdapter(Context context, List<eBookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_items, parent, false);
        return new EBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EBookViewHolder holder, int position) {

        holder.eBookName.setText(list.get(position).getPdfTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getPdfTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.eBookDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getPdfUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EBookViewHolder extends RecyclerView.ViewHolder {

        private TextView eBookName;
        private ImageView eBookDownload;

        public EBookViewHolder(@NonNull View itemView) {
            super(itemView);

            eBookDownload = itemView.findViewById(R.id.eBookDownload);
            eBookName = itemView.findViewById(R.id.eBookName);

        }

    }
}
