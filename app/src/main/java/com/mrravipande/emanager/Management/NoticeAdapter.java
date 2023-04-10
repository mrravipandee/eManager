package com.mrravipande.emanager.Management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrravipande.emanager.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {

    private Context context;
    private ArrayList<homeNotice> list;

    public NoticeAdapter(Context context, ArrayList<homeNotice> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_items, parent, false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {

        homeNotice currentItem = list.get(position);
        holder.tVdeleteNoticeTitle.setText(currentItem.getTitle());
        try {
            if (currentItem.getImage() != null) {
            Picasso.get().load(currentItem.getImage()).into(holder.iVdeleteNotice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.btnDeleteNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("EventNotices");
                reference.child(currentItem.getKey());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {

        private Button btnDeleteNotice;
        private TextView tVdeleteNoticeTitle;
        ImageView iVdeleteNotice;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);

            btnDeleteNotice = itemView.findViewById(R.id.btnDelete);
            tVdeleteNoticeTitle = itemView.findViewById(R.id.tVdeleteNoticeTitle);
            iVdeleteNotice = itemView.findViewById(R.id.iVdeleteNotice);

        }
    }

}
