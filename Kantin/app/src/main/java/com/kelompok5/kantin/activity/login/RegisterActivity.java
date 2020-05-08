package com.kelompok5.kantin.activity.login;

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

import com.kelompok5.kantin.R;
import com.kelompok5.kantin.helper.SqliteHelper;

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
                SQLiteDatabase db = SQLitedb.getWritableDatabase();
                ContentValues dataForm = new ContentValues();
                dataForm.put("nim_send", nim.getText().toString());
                dataForm.put("nama_lengkap", name.getText().toString());
                dataForm.put("telepon", hp.getText().toString());
                dataForm.put("kata_sandi", sandi.getText().toString());
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