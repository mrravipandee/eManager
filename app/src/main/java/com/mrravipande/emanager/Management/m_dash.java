package com.mrravipande.emanager.Management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;
import com.mrravipande.emanager.R;

public class m_dash extends AppCompatActivity {

    MaterialCardView homeNotice, certificates, photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdash);

        homeNotice = findViewById(R.id.homeNotice);
        certificates = findViewById(R.id.certificates);
        photos = findViewById(R.id.photos);

        homeNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_dash.this, com.mrravipande.emanager.Management.homeNotice.class);
                startActivity(intent);
            }
        });

        certificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_dash.this, com.mrravipande.emanager.Management.certificatesUpload.class);
                startActivity(intent);
            }
        });

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_dash.this, com.mrravipande.emanager.Management.photoUpload.class);
                startActivity(intent);
            }
        });
    }
}