package com.kelompok5.kantin.activity.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kelompok5.kantin.R;
import com.kelompok5.kantin.helper.SqliteHelper;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    protected Cursor cursor;
    SqliteHelper SQLitedb;
    Button daftar;
    EditText nim,name,hp,sandi;
    TextView kembali;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //nyatakan perumpamaahnya
        SQLitedb = new SqliteHelper(this);
        nim = (EditText) findViewById(R.id.editTextNim);
        name= (EditText) findViewById(R.id.editTextNama);
        hp = (EditText) findViewById(R.id.editTextTelepon);
        sandi = (EditText) findViewById(R.id.editTextSandi);
        daftar = (Button) findViewById(R.id.buttonDaftar);
        kembali = (TextView) findViewById(R.id.textViewKembali);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iki melalui sqlite
                SQLiteDatabase db = SQLitedb.getWritableDatabase();
                ContentValues dataForm = new ContentValues();
                dataForm.put("nim_send", nim.getText().toString());
                dataForm.put("nama_lengkap", name.getText().toString());
                dataForm.put("telepon", hp.getText().toString());
                dataForm.put("kata_sandi", sandi.getText().toString());

                //IKI LEWAT FIREBASE(sementara gae API iki)
                Map<String, String> userdatas = new HashMap<>();
                userdatas.put("nim_nip", nim.getText().toString());
                userdatas.put("nama_lengkap", name.getText().toString());
                userdatas.put("telepon", hp.getText().toString());
                userdatas.put("kata_sandi", sandi.getText().toString());

                try{
                    FirebaseFirestore fdb = FirebaseFirestore.getInstance();

                    fdb.collection("usersend")
                            .document()
                            .set(userdatas)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    System.out.println("success");
                                }
                             })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.println("Failed");
                                }
                            });

                }catch(Exception e){
                    e.printStackTrace();
                }

                boolean x = true;
                try {
                    db.insertOrThrow("tb_usersend", null, dataForm);
                } catch(Exception e) {
                    Toast.makeText(RegisterActivity.this, "Maaf, terjadi kesalahan di database", Toast.LENGTH_LONG).show();
                    x = false;
                    System.out.print(e.getMessage());
                } finally {
                    if(x){
                        Toast.makeText(RegisterActivity.this, "Selesai mendaftar", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
    }

}