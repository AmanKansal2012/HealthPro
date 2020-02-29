package com.example.healthpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class appointments extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<Users> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        recyclerView=findViewById(R.id.rv);

        ref= FirebaseDatabase.getInstance().getReference().child("Appointments");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ref!=null){

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        list=new ArrayList<>();
                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            list.add(ds.getValue(Users.class));

                        }
                        Adapterclass adapterclass=new Adapterclass(list);
                        recyclerView.setAdapter(adapterclass);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(appointments.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }




    }}
