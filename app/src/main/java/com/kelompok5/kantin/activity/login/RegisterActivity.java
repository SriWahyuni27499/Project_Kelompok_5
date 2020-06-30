package com.kelompok5.kantin.activity.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.util.Base64;
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

class UploadFile extends AsyncTask<String, Void, Void> {


    private String Content, image;
    private String Error = null;
    String data = "";
    private BufferedReader reader;


    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }

    protected void onPreExecute() {


        try {

            data += "&" + URLEncoder.encode("image", "UTF-8") + "=" + "data:image/png;base64," + image;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}


public class RegisterActivity extends AppCompatActivity {
    protected Cursor cursor;

    Button Bdaftar, uupload;
    EditText a, b, c, d, e, f, g;
    TextView Bkembali;
    String image;


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

        Bdaftar = (Button) findViewById(R.id.buttonDaftar);
        Bkembali = (TextView) findViewById(R.id.textViewKembali);
        uupload = findViewById(R.id.uploadfoto);

        uupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });


        Bdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    x = false;
                    System.out.print(e.getMessage());
                } finally {
                    if (x) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 0) {


            if (resultCode == RESULT_OK) {
                Uri targetUri = data.getData();
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

    }

    private void Upload() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new UploadFile().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://172.17.100.2/kantin/driver");
            } else {
                new UploadFile().execute("http://172.17.100.2/kantin/driver");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
