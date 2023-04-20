package com.mrravipande.emanager.Management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mrravipande.emanager.NoticeAdapter;
import com.mrravipande.emanager.NoticeFieldData;
import com.mrravipande.emanager.R;

import java.util.ArrayList;

public class NoticeDeleteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    NoticeAdapter noticeAdapter;
    ArrayList<NoticeFieldData> notice;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_delete);

        recyclerView = findViewById(R.id.deleteNoticeRecyc);
        progressBar = findViewById(R.id.progressBar);

        databaseReference = FirebaseDatabase.getInstance().getReference("EventNotices");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getNoticeData();
    }

    private void getNoticeData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notice = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NoticeFieldData noticeFieldData = snapshot.getValue(NoticeFieldData.class);
                    notice.add(noticeFieldData);
                }

                noticeAdapter = new NoticeAdapter(NoticeDeleteActivity.this, notice);
                noticeAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(noticeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                
                progressBar.setVisibility(View.GONE);

                Toast.makeText(NoticeDeleteActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}