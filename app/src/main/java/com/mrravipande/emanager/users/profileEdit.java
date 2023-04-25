package com.mrravipande.emanager.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrravipande.emanager.MainActivity;
import com.mrravipande.emanager.R;

public class profileEdit extends AppCompatActivity {

    EditText editName, editEmail, editUsername, editPassword;
    Button saveButton;
    String nameUser, emailUser, usernameUser, passwordUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        saveButton = findViewById(R.id.saveButton);

        showData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isPasswordChanged() || isEmailChanged()){
                    Toast.makeText(profileEdit.this, "Saved", Toast.LENGTH_SHORT).show();
                    Thread thread = new Thread() { // they thread wait for 2sec in profile activity for show a toast msg then go home.
                        public void run() {
                            try {
                                sleep(2000);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            finally {
                                Intent intent = new Intent(profileEdit.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    };thread.start();

                } else {
                    Toast.makeText(profileEdit.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                    Thread thread = new Thread() { // they thread wait for 2sec in profile activity for show a toast msg then go home.
                        public void run() {
                            try {
                                sleep(2000);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            finally {
                                Intent intent = new Intent(profileEdit.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    };thread.start();
                }
            }
        });

    }

    private boolean isNameChanged() {
        if (!nameUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if (!emailUser.equals(editEmail.getText().toString())){
            reference.child(usernameUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }


    private boolean isPasswordChanged() {
        if (!passwordUser.equals(editPassword.getText().toString())){
            reference.child(usernameUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void showData(){

        Intent intent = getIntent();

        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        usernameUser = intent.getStringExtra("username");
        passwordUser = intent.getStringExtra("password");

        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editUsername.setText(usernameUser);
        editPassword.setText(passwordUser);
    }

}