package com.kelompok5.kantin.activity.profil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kelompok5.kantin.R;
import com.kelompok5.kantin.activity.beranda.Beranda;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import io.grpc.internal.IoUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Profil extends AppCompatActivity {

    protected Cursor cursor;
    TextView user,namae,nohpmu,alamate,emaile,hitungan;
    Button metu,rubahen;
    CircleImageView imgUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //
        user = (TextView) findViewById(R.id.username);
        namae = (TextView) findViewById(R.id.nama);
        alamate = (TextView) findViewById(R.id.alamat);
        emaile = (TextView) findViewById(R.id.email);
        nohpmu = (TextView) findViewById(R.id.notepon);
        hitungan = (TextView) findViewById(R.id.count);

        metu = (Button) findViewById(R.id.keluar);
        rubahen = (Button) findViewById(R.id.ubah);

        imgUpload = findViewById(R.id.profilkugambar);

        final ProgressDialog pdig = new ProgressDialog(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pdig.setMessage("Mengambil Data, mohon tunggu...");
                pdig.setTitle("Proses");
                pdig.show();
            }
        });

        Runnable getData = new Runnable() {
            @Override
            public void run() {
                SharedPreferences username = getSharedPreferences("login", MODE_PRIVATE);
                final String userString = username.getString("username", "");

                RequestBody reqBody = new MultipartBody.Builder()
                                            .setType(MultipartBody.FORM)
                                            .addFormDataPart("username", userString)
                                            .build();
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url("http://172.17.100.2/kantin/driver_info").post(reqBody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if(response.code() == 200){
                            try{
                                final JSONObject jsonObject = new JSONObject(response.body().string());
                                Profil.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try{
                                            user.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("username"));
                                            namae.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("nama_driver"));
                                            alamate.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("alamat"));
                                            emaile.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("email"));
                                            nohpmu.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("no_telephone"));
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }finally{
                                            try{
                                                OkHttpClient newClient = new OkHttpClient();
                                                Request req = new Request.Builder().url("http://172.17.100.2/kantin/assets/"+userString+"/"+userString+".png").build();
                                                newClient.newCall(req).enqueue(new Callback() {
                                                    @Override
                                                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                                        e.printStackTrace();
                                                    }

                                                    @Override
                                                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                                        if(response.isSuccessful()){
                                                            if(response.body().contentLength() != 0){
                                                                InputStream in = response.body().byteStream();
                                                                byte[] gambar = IoUtils.toByteArray(in);
                                                                final Bitmap decodedByte = BitmapFactory.decodeByteArray(gambar, 0, gambar.length);
                                                                runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        imgUpload.setImageBitmap(decodedByte);
                                                                    }
                                                                });
                                                            }
                                                        }else{
                                                            System.out.println(response.body());
                                                        }
                                                    }
                                                });
                                            }catch(Exception e){
                                                e.printStackTrace();
                                            }finally {
                                                try {
                                                    SharedPreferences username = getSharedPreferences("login", MODE_PRIVATE);
                                                    String userString = username.getString("username", "");

                                                    JSONObject jsonObject = new JSONObject();

                                                    try {
                                                        jsonObject.put("username", userString);
                                                    }catch (Exception e){

                                                    }

                                                    OkHttpClient client = new OkHttpClient();
                                                    RequestBody req = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

                                                    Request reqq = new Request.Builder().url("http://172.17.100.2/kantin/count").post(req).build();

                                                    client.newCall(reqq).enqueue(new Callback() {
                                                        @Override
                                                        public void onFailure(@NotNull Call call, @NotNull IOException e) {

                                                        }

                                                        @Override
                                                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                                            try{
                                                                JSONObject jsonObject = new JSONObject(response.body().string());
                                                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                                final String hitungannya = jsonArray.getJSONObject(0).get("jumlah").toString();
                                                                runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        hitungan.setText(hitungannya + " Pesanan");
                                                                    }
                                                                });
                                                            }catch (Exception e){

                                                            }
                                                        }
                                                    });

                                                }catch (Exception e){

                                                }
                                            }
                                        }
                                    }
                                });
                            }catch (JSONException e){
                                e.printStackTrace();
                            }catch(Exception e){
                                e.printStackTrace();
                            }finally{
                                Profil.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pdig.hide();
                                    }
                                });
                            }

                        }else{
                            Profil.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pdig.hide();
                                    AlertDialog.Builder hbuild = new AlertDialog.Builder(Profil.this);
                                    hbuild.setTitle("No Data").setMessage("Tidak ada data yang terkait").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                                }
                            });

                        }
                    }
                });
            }
        };

        Thread x = new Thread(getData);
        x.start();

        SharedPreferences username = getSharedPreferences("login", MODE_PRIVATE);
        final String userString = username.getString("username", "");


        rubahen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(Profil.this, Edit.class);
                edit.putExtra("nim_nip", userString);
                startActivity(edit);
            }
        });
        metu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent balikberanda = new Intent(Profil.this, Beranda.class);
                startActivity(balikberanda);
            }
        });
    }
}
