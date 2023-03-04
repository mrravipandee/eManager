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

    TextView profileName, profileEmail, profileUsername, profilePassword;
    TextView titleName, titleUsername;
    Button editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);

        profileName = findViewById(R.id.titleName);
        profileUsername = findViewById(R.id.studentId);

        showAllUserData();

//        editProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                passUserData();
//            }
//        });

    }

    public void showAllUserData(){
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("name");
        String usernameUser = intent.getStringExtra("username");

        profileName.setText(nameUser);
        profileUsername.setText(usernameUser);
    }

//    public void passUserData(){
//        String userUsername = profileUsername.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
//
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if (snapshot.exists()){
//
//                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
//                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
//
//                    Intent intent = new Intent(navHeader.this, profileEdit.class);
//
//                    intent.putExtra("name", nameFromDB);
//                    intent.putExtra("username", usernameFromDB);
//
//                    startActivity(intent);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}