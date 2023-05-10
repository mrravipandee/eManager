package com.mrravipande.emanager.Management;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrravipande.emanager.FullZoomImages;
import com.mrravipande.emanager.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {
    Context context;
    ArrayList<NoticeFieldData> NoticeData;

    public NoticeAdapter(Context context, ArrayList<NoticeFieldData> noticeData) {
        this.context = context;
        this.NoticeData = noticeData;
    }

    @NonNull
    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_items, parent, false);
        return new NoticeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NoticeFieldData noticeFieldData = NoticeData.get(position);

        holder.nTitle.setText(noticeFieldData.getEventTitle());
        holder.nDiscription.setText(noticeFieldData.getEventDesciption());
        holder.nLocation.setText(noticeFieldData.getEventLocation());
        holder.nCollege.setText(noticeFieldData.getEventCollege());
        holder.nDate.setText(noticeFieldData.getEventDate());
        

        try {
            if(noticeFieldData.getImage() != null)
                Picasso.get().load(noticeFieldData.getImage()).into(holder.nImageview);
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.nDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("EventNotices");
                reference.child(noticeFieldData.getKey()).removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Deleted...", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show();
                            }
                        });
                        notifyItemRemoved(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return NoticeData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView nTitle, nDate, nDiscription, nLocation, nCollege, nRegLink;
       Button nDelete;
       ImageView nImageview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nTitle = itemView.findViewById(R.id.titleNotice);
            nDiscription = itemView.findViewById(R.id.discOfNotice);
            nLocation = itemView.findViewById(R.id.discOfLocation);
            nCollege = itemView.findViewById(R.id.collegeOfNotice);
            nDate = itemView.findViewById(R.id.dateOfNotice);

            nDelete = itemView.findViewById(R.id.btnDeleteNotice);
            nImageview = itemView.findViewById(R.id.imageOfNotice);
        }
    }


}