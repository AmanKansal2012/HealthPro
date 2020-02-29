package com.example.healthpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class dash extends AppCompatActivity {

    Switch s1;
    CardView b1, c1, c2;
    ImageView imglog;

    RelativeLayout relativeLayout;
    DatabaseReference mUser = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        c2 = findViewById(R.id.card3);
        relativeLayout = findViewById(R.id.toplayout);
        s1 = findViewById(R.id.switch1);
        s1.setChecked(true);
        s1.setTextOff("OFF");
        s1.setTextOn("ON");


        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (s1.isChecked()) {
                    Toast.makeText(dash.this, "button is checked", Toast.LENGTH_SHORT).show();

                } else {
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    assert currentUser != null;
                    String uid = currentUser.getUid();
                    mUser = FirebaseDatabase.getInstance().getReference().child("DoctorsRegister").child(uid);
                    mUser.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(dash.this, "Offline", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(dash.this, login.class);
                            startActivity(intent);

                        }
                    });


                }


            }
        });
        b1 = findViewById(R.id.card1);
        c1 = findViewById(R.id.card2);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dash.this, write.class);
                startActivity(intent);

            }
        });
        imglog = findViewById(R.id.logout);
        imglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(dash.this, login.class);
                startActivity(intent);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dash.this, appointments.class);
                startActivity(intent);
            }
        });


    }


    public void goemergency(View view) {
        Intent intent = new Intent(dash.this, emergency.class);
        startActivity(intent);

    }
}
