package com.kelompok5.kantin.activity.profil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kelompok5.kantin.R;
import com.kelompok5.kantin.activity.login.Login;
import com.kelompok5.kantin.activity.login.LoginActivity;
import com.kelompok5.kantin.helper.SqliteHelper;

import org.w3c.dom.Text;

public class Profil extends AppCompatActivity {

    protected Cursor cursor;
    SqliteHelper sql;
    TextView user,namae,nohpmu;
    Button metu,rubahen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

//        sql= new SqliteHelper(this);
        user = (TextView) findViewById(R.id.nim);
        namae = (TextView) findViewById(R.id.nama);
        nohpmu = (TextView) findViewById(R.id.nohp);
        metu = (Button) findViewById(R.id.keluar);
        rubahen = (Button) findViewById(R.id.ubah);



        SharedPreferences username = getSharedPreferences("login", MODE_PRIVATE);
        final String userString = username.getString("username", "");

        FirebaseFirestore fdb = FirebaseFirestore.getInstance();

        metu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profil.this.finish();
            }
        });

        rubahen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(Profil.this, Edit.class);
                edit.putExtra("nim_nip", userString);
                startActivity(edit);
            }
        });


        fdb.collection("usersend")
                .whereEqualTo("nim_nip", userString)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot a : task.getResult()){
                            user.setText(a.getData().get("nim_nip").toString());
                            namae.setText(a.getData().get("nama_lengkap").toString());
                            nohpmu.setText(a.getData().get("telepon").toString());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });



    }
}
