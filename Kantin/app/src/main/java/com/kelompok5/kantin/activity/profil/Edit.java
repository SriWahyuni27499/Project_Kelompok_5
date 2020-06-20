package com.kelompok5.kantin.activity.profil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kelompok5.kantin.R;
import com.kelompok5.kantin.activity.beranda.Beranda;
import com.kelompok5.kantin.helper.SqliteHelper;

import java.util.HashMap;
import java.util.Map;

public class Edit extends AppCompatActivity {

    protected Cursor cursor;
    SqliteHelper sql;
    TextView namae,nohpmu,key;
    Button mari,rasedo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        namae = (EditText) findViewById(R.id.namamu);
        nohpmu = (EditText) findViewById(R.id.nomermu);
        key = (EditText) findViewById(R.id.sandimu);
        mari = (Button) findViewById(R.id.gaskan);
        rasedo = (Button) findViewById(R.id.gajadi);

        FirebaseFirestore fdb = FirebaseFirestore.getInstance();

        rasedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selesai = new Intent(Edit.this, Profil.class);
                startActivity(selesai);
            }
        });

        String nimNip = getIntent().getStringExtra("nim_nip");

        fdb.collection("usersend")
                .whereEqualTo("nim_nip", nimNip)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot a : task.getResult()){
                            namae.setText(a.getData().get("nama_lengkap").toString());
                            nohpmu.setText(a.getData().get("telepon").toString());
                            key.setText(a.getData().get("kata_sandi").toString());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        Map<String, String> data = new HashMap<>();

        data.put("nama_lengkap", namae.getText().toString());
        data.put("telepon", nohpmu.getText().toString());
        data.put("kata_sandi", key.getText().toString());


        final DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("usersend");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(String.valueOf(dataSnapshot.exists()));
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        ref.child(data.getKey()).child("nim_nip").setValue("E41180707");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
