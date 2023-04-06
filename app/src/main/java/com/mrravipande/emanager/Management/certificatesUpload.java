package com.mrravipande.emanager.Management;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.io.File;
import java.util.HashMap;

public class certificatesUpload extends AppCompatActivity {

    private MaterialCardView selectPdf;
    private final int REQ = 1;
    private EditText pdfTitle;
    private Button uploadPdf;
    private String pdfName, title;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private ProgressDialog pd;
    private Uri pdfData;
    private TextView pdfTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates_upload);

        selectPdf = findViewById(R.id.addPdfSelect);
        pdfTitle = findViewById(R.id.pdfTitle);
        uploadPdf = findViewById(R.id.uploadPdfBtn);
        pdfTextview = findViewById(R.id.pdfTextview);

        pd = new ProgressDialog(this);

        //firebase variables
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        selectPdf.setOnClickListener(view -> openGallery());

        uploadPdf.setOnClickListener(view -> {
            title = pdfTitle.getText().toString();
            if (title.isEmpty()) {
                pdfTitle.setError("Empty");
                pdfTitle.requestFocus();
            } else if (pdfData == null) {
                Toast.makeText(certificatesUpload.this, "Please Upload PDF", Toast.LENGTH_SHORT).show();
            } else {
                uploadPdf();
            }
        });

    }
    private void uploadPdf() {
        pd.setTitle("Please waiting...");
        pd.setMessage("Uploading PDF");
        pd.show();

        StorageReference reference = storageReference.child("PDF/"+pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri = uriTask.getResult();
                        uploadData(String.valueOf(uri));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(certificatesUpload.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData(String downloadUrl) {
        String uniqueKey = databaseReference.child("PDF").push().getKey();

        HashMap data = new HashMap();
        data.put("pdfTitle", title);
        data.put("pdfUrl", downloadUrl);

        databaseReference.child("pdf").child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(certificatesUpload.this, "PDF uploaded successfully", Toast.LENGTH_SHORT).show();
                pdfTitle.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(certificatesUpload.this, "Failed to upload PDF", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("application/pdf"); //* -> "pdf/docs/ppt", * mean select all kind documents
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ && resultCode == RESULT_OK) {
             pdfData = data.getData();

             if (pdfData.toString().startsWith("content://")) {
                 Cursor cursor = null;
                 try {
                     cursor = certificatesUpload.this.getContentResolver().query(pdfData, null, null, null, null);
                     if (cursor != null && cursor.moveToFirst()) {
                         int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                         pdfName = cursor.getString(nameIndex);
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }

             } else if (pdfData.toString().startsWith("file://")) {
                 pdfName = new File(pdfData.toString()).getName();
                 
             }
             pdfTextview.setText(pdfName);

        }
    }

}