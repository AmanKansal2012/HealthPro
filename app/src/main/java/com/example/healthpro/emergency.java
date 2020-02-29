package com.example.healthpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class emergency extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<User> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        recyclerView=findViewById(R.id.rv1);
        ref= FirebaseDatabase.getInstance().getReference().child("Emergency");
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (ref!=null){

            ref.orderByChild("Emergency").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        list=new ArrayList<>();
                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            list.add(ds.getValue(User.class));

                        }
                        Adapterclas adapterclass=new Adapterclas(list);
                        recyclerView.setAdapter(adapterclass);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(emergency.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }




    }
}
