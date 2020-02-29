package com.example.healthpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class login extends AppCompatActivity {
    EditText edit1,edit2,edit3,edit4,Dom;
    Button bt;
    ProgressDialog mLoginProgress;
    FirebaseAuth mAuth;
    private DatabaseReference mUser = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit1 = findViewById(R.id.log_email);
        edit2 = findViewById(R.id.log_Password);
        edit3 = findViewById(R.id.log_name);
        edit4=findViewById(R.id.log_number);
        Dom=findViewById(R.id.domainn);
        bt=findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        mLoginProgress = new ProgressDialog(this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Username = edit3.getText().toString();
                String password = edit2.getText().toString();
                final  String number= edit4.getText().toString();
                final String email = edit1.getText().toString();
                final String Do=Dom.getText().toString();
                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)||TextUtils.isEmpty(Username)||TextUtils.isEmpty(number)||TextUtils.isEmpty(Do)){
                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please wait while we check your credentials.");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();


                    loginUser(email, password,Username,number,Do);

                }

            }
        });

    }

    private void loginUser(final String email, String password, final String Usename, final String number,final String Do) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mLoginProgress.dismiss();
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            assert currentUser != null;
                            String uid = currentUser.getUid();
                            HashMap<String,String> userDetails = new HashMap<>();
                            userDetails.put("name",Usename);
                            userDetails.put("email",email);
                            userDetails.put("number",number);
                            userDetails.put("id",uid);
                            userDetails.put("Domain",Do);
                            mUser.child("DoctorsRegister").child(uid).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    String current_user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                                    Toast.makeText(login.this, "Hey Welcome Back", Toast.LENGTH_SHORT).show();


                                    Intent lintent = new Intent(login.this, dash.class);
                                    lintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(lintent);
                                    finish();

                                }
                            });



                        } else {

                            Toast.makeText(login.this, "Failed to login", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });


    }

    public void txtreg(View view) {
        Intent intent=new Intent(login.this,register.class);
        startActivity(intent);
        finish();
    }
}
