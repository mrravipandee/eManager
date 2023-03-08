package com.mrravipande.emanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class navHeader extends AppCompatActivity {
    TextView titleName, titleUsername;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");



        titleName = findViewById(R.id.titleName);
        titleUsername = findViewById(R.id.studentId);

        showAllUserData();

    }

    public void showAllUserData(){

        Intent intent1 = getIntent();
        String nameUser = intent1.getStringExtra("name");
        String usernameUser = intent1.getStringExtra("username");

        titleName.setText(nameUser);
        titleUsername.setText(usernameUser);
    }

}