package com.example.healthpro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ImageView splashscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        splashscreen = findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.myanim);
        splashscreen.startAnimation(animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser == null) {

                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                    finish();


                } else {
                    Intent homeinent = new Intent(MainActivity.this, dash.class);
                    startActivity(homeinent);
                    finish();

                }


            }


        }, 3000);

    }

}

