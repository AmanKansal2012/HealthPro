package com.example.healthpro;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Objects;

public class register extends AppCompatActivity {

    EditText txt1, txt2, txt3, Email,Domain;

    Button rgbtn;

    FirebaseAuth mAuth;

    private DatabaseReference mUser = FirebaseDatabase.getInstance().getReference();
    private ProgressDialog mRegProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt1 = findViewById(R.id.name);
        txt2 = findViewById(R.id.password);
        txt3 = findViewById(R.id.number);
        Email = findViewById(R.id.email);
        Domain=findViewById(R.id.domain);
        mRegProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        rgbtn = findViewById(R.id.button_register);


        rgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Username = txt1.getText().toString();
                final String password = txt2.getText().toString();
                final String number = txt3.getText().toString();
                final String dom=Domain.getText().toString();
                final String email = Email.getText().toString();
                if (email.isEmpty() || Username.isEmpty() || password.isEmpty() ||dom.isEmpty()) {

                    showMessage("Please Verify all fields");

                    // something goes wrong : all fields must be filled
                    // we need to display an error message

                } else {
                    // everything is ok and all fields are filled now we can start creating user account
                    // CreateUserAccount method will try to create the user if the email is valid

                    CreateUserAccount(email, Username, password,dom);
                }

            }
        });




    }

    private void CreateUserAccount(final String email, final String username, final String password,final String dom) {

        mRegProgress.setTitle("Registering User");
        mRegProgress.setMessage("Please wait registration depends on image size!");
        mRegProgress.setCanceledOnTouchOutside(false);
        mRegProgress.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            assert currentUser != null;
                            String uid = currentUser.getUid();
                            HashMap<String, String> userDetails = new HashMap<>();
                            userDetails.put("Name", username);
                            userDetails.put("Email", email);
                            userDetails.put("Domain",dom);
                            userDetails.put("id",uid);
                            mUser.child("DoctorsRegister").child(uid).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    mRegProgress.dismiss();
                                    Toast.makeText(register.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), dash.class));

                                }
                            });





                        } else {
                            showMessage("account creation failed" + Objects.requireNonNull(task.getException()).getMessage());

                            // account creation failed


                        }
                    }
                });

    }




    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }


}

