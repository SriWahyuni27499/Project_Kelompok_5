package com.kelompok5.kantin.activity.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kelompok5.kantin.R;


import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    ProgressDialog pDialog;
    String image, username;
    protected Cursor cursor;

    Button Bdaftar, uupload;
    EditText a, b, c, d, e, f, g;
    TextView Bkembali;
    Intent intents;
    int rescode, resStat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //nyatakan perumpamaahnya

//        a = (EditText) findViewById(R.id.id);
        b = (EditText) findViewById(R.id.editTextNama);
        c = (EditText) findViewById(R.id.editTextalamat);
        d = (EditText) findViewById(R.id.editTextuname);
        e = (EditText) findViewById(R.id.editTextSandi);
        f = (EditText) findViewById(R.id.editTextemail);
        g = (EditText) findViewById(R.id.editTextTelepon);

        d.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        InputFilter letterFilter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String filtered = "";
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (!Character.isWhitespace(character)&&Character.isLetter(character)) {
                        filtered += character;
                    }
                }

                return filtered;
            }

        };
        b.setFilters(new InputFilter[]{letterFilter});


        Bdaftar = (Button) findViewById(R.id.buttonDaftar);
        Bkembali = (TextView) findViewById(R.id.textViewKembali);
        uupload = findViewById(R.id.uploadfoto);

        uupload.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           new AlertDialog.Builder(RegisterActivity.this).setTitle("Costumer Service").setMessage("Akan sulit untuk mengubah foto profil anda untuk kedua kalinya. Harap memilih foto formal!")
                                                   .setNegativeButton("Lanjutkan", new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialog, int which) {
                                                           Intent intent = new Intent(Intent.ACTION_PICK,
                                                                   android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                                           startActivityForResult(intent, 0);

                                                       }
                                                   })
                                                   .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialog, int which) {

                                                       }
                                                   }).show();


                                       };



        });


        Bdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b.getText().toString().length() == 0) {
                    //jika form Email belum di isi / masih kosong
                    b.setError("Harap mengisi Nama Lengkap anda!");
                } else if (c.getText().toString().length() == 0) {
                    //jika form Username belum di isi / masih kosong
                    c.setError("Harap mengisi Alamat anda!");
                } else if (d.getText().toString().length() == 0) {
                    //jika form Passwrod belum di isi / masih kosong
                    d.setError("Harap mengisi NIM anda!");
                } else if (e.getText().toString().length() == 0) {
                    //jika form Passwrod belum di isi / masih kosong
                    e.setError("Harap mengisi Kata Sandi anda!");
                } else if (f.getText().toString().length() == 0) {
                    //jika form Passwrod belum di isi / masih kosong
                    f.setError("Harap mengisi Emai anda!");
                } else if (g.getText().toString().length() == 0) {
                    //jika form Passwrod belum di isi / masih kosong
                    g.setError("Harap mengisi No. Telepon anda!");
                } else {

                new AlertDialog.Builder(RegisterActivity.this).setTitle("Costumer Service").setMessage("Apakah data yang anda masukkan sudah valid? tekan OK untuk melanjutkan")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                username = d.getText().toString();

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

                                                if (response.code()==400){
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            AlertDialog.Builder abi = new AlertDialog.Builder(RegisterActivity.this);
                                                            abi.setTitle("Admin");
                                                            abi.setMessage("p");
                                                            abi.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.dismiss();
                                                                }
                                                            });
                                                            abi.show();
                                                        }
                                                    });

                                                    return;

                                                }


                                                if (intents != null && rescode == 0) {
                                                    if (resStat == RESULT_OK) {
                                                        Uri targetUri = intents.getData();
                                                        Bitmap bitmap;
                                                        try {
                                                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                                                            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);
                                                            image = ConvertBitmapToString(resizedBitmap);

                                                            Upload();

                                                        } catch (FileNotFoundException e) {
                                                            // TODO Auto-generated catch block
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }


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

                                    } catch (Exception e) {
                                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        x = false;
                                        System.out.print(e.getMessage());
                                    } finally {
                                        if (x) {
                                            Toast.makeText(RegisterActivity.this, "Selesai mendaftar", Toast.LENGTH_LONG).show();
                                        }
                                        //       }
                                    }
                            }
                        })
                        .setPositiveButton("Cek Kembali", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        intents = data;
        rescode = requestCode;
        resStat = resultCode;
    }

    public static String ConvertBitmapToString(Bitmap bitmap) {
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodedImage;
    }


    private void Upload() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new UploadFile(RegisterActivity.this, image, username).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://172.17.100.2/kantin/coba.php");
            } else {
                new UploadFile(RegisterActivity.this, image, username).execute("http://172.17.100.2/kantin/coba.php");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
