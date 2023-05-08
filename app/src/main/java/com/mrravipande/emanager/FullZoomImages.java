package com.mrravipande.emanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullZoomImages extends AppCompatActivity {

    private ImageView imageView;
    private Button regLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_zoom_images);

        imageView = findViewById(R.id.imageView);
        regLink = findViewById(R.id.regLink);

        String image = getIntent().getStringExtra("image");

        Glide.with(this).load(image).into(imageView);
    }
}