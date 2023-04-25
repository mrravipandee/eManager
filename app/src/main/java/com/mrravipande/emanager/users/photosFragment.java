package com.mrravipande.emanager.users;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mrravipande.emanager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class photosFragment extends Fragment {

    RecyclerView sports, inauguration, events, others;
    imageAdapter adapter;

    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photos, container, false);

        sports = view.findViewById(R.id.image_recycler_sport);
        inauguration = view.findViewById(R.id.image_recycler_inauguration);
        events = view.findViewById(R.id.image_recycler_events);
        others = view.findViewById(R.id.image_recycler_others);

        reference = FirebaseDatabase.getInstance().getReference().child("Photos");

        getSportsImages();
        getInaugurationImages();
        getEventsImages();
        getOthersImages();

        return view;
    }

    private void getSportsImages() {
        reference.child("Sports").addValueEventListener(new ValueEventListener() {

            List<String> imagesList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String data = (String) snapshot.getValue().toString();
                    imagesList.add(data);
                }


                adapter = new imageAdapter(getContext(), imagesList);
                sports.setLayoutManager(new GridLayoutManager(getContext(), 3));
                sports.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getInaugurationImages() {
        reference.child("Inauguration").addValueEventListener(new ValueEventListener() {

            List<String> imagesList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String data = (String) snapshot.getValue().toString();
                    imagesList.add(data);
                }

                adapter = new imageAdapter(getContext(), imagesList);
                inauguration.setLayoutManager(new GridLayoutManager(getContext(), 3));
                inauguration.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void getEventsImages() {
        reference.child("Events").addValueEventListener(new ValueEventListener() {

            List<String> imagesList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String data = (String) snapshot.getValue();
                    imagesList.add(data);
                }

                adapter = new imageAdapter(getContext(), imagesList);
                events.setLayoutManager(new GridLayoutManager(getContext(), 3));
                events.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void getOthersImages() {
        reference.child("Others").addValueEventListener(new ValueEventListener() {

            List<String> imagesList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String data = (String) snapshot.getValue().toString();
                    imagesList.add(data);
                }

                adapter = new imageAdapter(getContext(), imagesList);
                others.setLayoutManager(new GridLayoutManager(getContext(), 3));
                others.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}