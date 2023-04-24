package com.mrravipande.emanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;


public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.MyViewHolder> {

    Context context;
    ArrayList<NoticeDataHome> NoticeData;

    public HomeFeedAdapter(Context context, ArrayList<NoticeDataHome> noticeData) {
        this.context = context;
        this.NoticeData = noticeData;
    }

    @NonNull
    @Override
    public HomeFeedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_notice_feed, parent, false);
        return new HomeFeedAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NoticeDataHome noticeFieldData = NoticeData.get(position);

        holder.nTitle.setText(noticeFieldData.getEventTitle());
        holder.nDiscription.setText(noticeFieldData.getEventDesciption());
        holder.nLocation.setText(noticeFieldData.getEventLocation());
        holder.nCollege.setText(noticeFieldData.getEventCollege());
        holder.nDate.setText(noticeFieldData.getEventDate());

        try {
            if (noticeFieldData.getImage() != null)
                Glide.with(context).load(noticeFieldData.getImage()).into(holder.nImageview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return NoticeData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nTitle, nDate, nDiscription, nLocation, nCollege;
        ImageView nImageview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nTitle = itemView.findViewById(R.id.titleNotice);
            nDiscription = itemView.findViewById(R.id.discOfNotice);
            nLocation = itemView.findViewById(R.id.discOfLocation);
            nCollege = itemView.findViewById(R.id.collegeOfNotice);
            nDate = itemView.findViewById(R.id.dateOfNotice);

            nImageview = itemView.findViewById(R.id.imageOfNotice);
        }
    }
}

