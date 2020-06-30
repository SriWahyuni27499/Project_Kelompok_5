package com.kelompok5.kantin.activity.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    protected Cursor cursor;

    Button Bdaftar;
    EditText a,b,c,d,e,f,g,h;
    TextView Bkembali;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //nyatakan perumpamaahnya

//        a = (EditText) findViewById(R.id.id);
        b= (EditText) findViewById(R.id.editTextNama);
        c = (EditText) findViewById(R.id.editTextalamat);
        d = (EditText) findViewById(R.id.editTextuname);
        e = (EditText) findViewById(R.id.editTextSandi);
        f = (EditText) findViewById(R.id.editTextemail);
        g = (EditText) findViewById(R.id.editTextTelepon);

        Bdaftar = (Button) findViewById(R.id.buttonDaftar);
        Bkembali = (TextView) findViewById(R.id.textViewKembali);

        Bdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iki melalui sqlite
//                SQLiteDatabase db = SQLitedb.getWritableDatabase();
//                ContentValues dataForm = new ContentValues();
//                dataForm.put("id_driver", a.getText().length());
//                dataForm.put("nama_driver", b.getText().toString());
//                dataForm.put("alamat", c.getText().toString());
//                dataForm.put("usernmame", d.getText().toString());
//                dataForm.put("password", e.getText().toString());
//                dataForm.put("email", f.getText().toString());
//                dataForm.put("no_telephone", g.getText().toString());



                //IKI LEWAT FIREBASE(sementara gae API iki)
                boolean x = true;
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody reqBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("nama_driver", b.getText().toString())
                            .addFormDataPart("alamat", c.getText().toString())
                            .addFormDataPart("username", d.getText().toString())
                            .addFormDataPart("password", e.getText().toString())
                            .addFormDataPart("email", f.getText().toString())
                            .addFormDataPart("no_telephone", g.getText().toString())
                            .addFormDataPart("foto", "null")
                            .build();
                    Request request = new Request.Builder().url("http://172.17.100.2/kantin/driver").post(reqBody).build();

                    //db.insertOrThrow("tb_driver", null, dataForm);
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            RegisterActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alig = new AlertDialog.Builder(RegisterActivity.this);
                                    alig.setMessage("Gagal mendaftarkan akun").setTitle("Gagal").show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            RegisterActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder alig = new AlertDialog.Builder(RegisterActivity.this);
                                    alig.setMessage("Berhasil mendaftarkan akun").setTitle("Sukses").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    }).show();
                                }
                            });

                        }
                    });

                } catch(Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    x = false;
                    System.out.print(e.getMessage());
                } finally {
                    if(x){
                        Toast.makeText(RegisterActivity.this, "Selesai mendaftar", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        Bkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
    }

}