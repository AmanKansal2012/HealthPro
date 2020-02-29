package com.example.healthpro;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.HashMap;

public class write extends AppCompatActivity {
    EditText ptname,ptdisease,ptprescribe,ptprior;
    private ProgressDialog mRegProgress;
    Button b2;
    int n=1;
    private DatabaseReference mUser = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ptname=findViewById(R.id.ptname);
        ptdisease=findViewById(R.id.disease);
        ptprescribe=findViewById(R.id.prescribtion);
        mRegProgress = new ProgressDialog(this);
        ptprior=findViewById(R.id.prior);
        b2=findViewById(R.id.btn_write);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String patientname=ptname.getText().toString();
                String patientdisease=ptdisease.getText().toString();
                String patientprescribtione=ptprescribe.getText().toString();
                String patientprioredetail=ptprior.getText().toString();

                if(!TextUtils.isEmpty(patientname) ||!TextUtils.isEmpty(patientdisease)||TextUtils.isEmpty(patientprescribtione)||TextUtils.isEmpty(patientprioredetail)){

                    createUpload(patientname,patientdisease,patientprescribtione,patientprioredetail);

                }

            }
        });
    }

    private void createUpload(String patientname, String patientdisease, String patientprescribtione, String patientprioredetail) {

        mRegProgress.setTitle("Registering User");
        mRegProgress.setMessage("Please wait registration depends on image size!");
        mRegProgress.setCanceledOnTouchOutside(false);
        mRegProgress.show();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        assert currentUser != null;
        String uid = currentUser.getUid();
        HashMap<String,String> userDetails = new HashMap<>();
        userDetails.put("Name",patientname);
        userDetails.put("Disease",patientdisease);
        userDetails.put("Prescribtion",patientprescribtione);
        userDetails.put("history",patientprioredetail);
        userDetails.put("id",uid);
        mRegProgress.dismiss();
        mUser.child("Write").child(uid).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                n++;
                Toast.makeText(write.this,"Form created successfully",Toast.LENGTH_SHORT).show();


            }
        });



    }
}
