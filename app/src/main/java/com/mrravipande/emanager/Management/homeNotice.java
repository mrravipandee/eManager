package com.mrravipande.emanager.Management;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mrravipande.emanager.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class homeNotice extends AppCompatActivity {
    
    private MaterialCardView addImageNotice;
    private final int REQ = 1;
    private Bitmap bitmap;
    private ImageView showImageView;
    private EditText eventTitle, eventDesciption, eventLocation, eventDate, eventCollege;
    private Button postEventBtn;

    private DatabaseReference reference;
    private StorageReference storageReference;
    String downloadUrl = "".toString();
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_notice);

        eventTitle = findViewById(R.id.eventTitle);
        eventDesciption = findViewById(R.id.eventDesciption);
        eventLocation = findViewById(R.id.eventLocation);
        eventDate = findViewById(R.id.eventDate);
        eventCollege = findViewById(R.id.eventCollege);
        postEventBtn = findViewById(R.id.postEvent);
        showImageView = findViewById(R.id.noticeImageview);
        addImageNotice = findViewById(R.id.addImageNotice);
        pd = new ProgressDialog(this);

        //firebase variables
        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        addImageNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        postEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eventTitle.getText().toString().isEmpty()) {
                    eventTitle.setError("Please Fill");
                    eventTitle.requestFocus();
                } else if (bitmap == null) {

                    Toast.makeText(homeNotice.this, "Please select an image.", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage(); // upload images in firebase storage & other data also because our updatedata() used in a uploadImg() function.
                }
            }
        });
    }

    private void uploadImage() {
        //show progress status
        pd.setMessage("Uploading...");
        pd.show();

        //reduce image size
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;

        //store in Database
        filePath = storageReference.child("NoticesImg").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(homeNotice.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    uploadData();
                                }
                            });
                        }
                    });
                } else {
                    pd.dismiss();
                    Toast.makeText(homeNotice.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadData() { //to store all info about event.

        reference = reference.child("EventNotices");

        final String uniqueKey = reference.push().getKey();

        String eTitle = eventTitle.getText().toString();
        String eDescription = eventDesciption.getText().toString();
        String eLocation = eventLocation.getText().toString();
        String eDate = eventDate.getText().toString();
        String eCollege = eventCollege.getText().toString();

        Calendar calDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(calDate.getTime());

        Calendar calTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(calTime.getTime());

        EventDataInDB eventDataInDB = new EventDataInDB(downloadUrl, eTitle, eDescription, eLocation, eDate, eCollege, uniqueKey, date, time);

        reference.child(uniqueKey).setValue(eventDataInDB).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(homeNotice.this, "Notice Published", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(homeNotice.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // show image in imageview
            showImageView.setImageBitmap(bitmap);
        }
    }
}