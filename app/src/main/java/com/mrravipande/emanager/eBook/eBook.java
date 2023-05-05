package com.mrravipande.emanager.eBook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mrravipande.emanager.R;

import java.util.ArrayList;
import java.util.List;

public class eBook extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private List<eBookData> list;
    private eBookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        recyclerView = findViewById(R.id.eBookRecycle);
        reference = FirebaseDatabase.getInstance().getReference().child("pdf");

        getData();
    }

    private void getData() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    eBookData data = snapshot.getValue(eBookData.class);
                    list.add(data);
                }
                adapter = new eBookAdapter(eBook.this, list);
                recyclerView.setLayoutManager(new LinearLayoutManager(eBook.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(eBook.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}