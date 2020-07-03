package com.kelompok5.kantin.activity.beranda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kelompok5.kantin.R;
import com.kelompok5.kantin.activity.history.Histori;
import com.kelompok5.kantin.activity.login.LoginActivity;
import com.kelompok5.kantin.activity.pesananan.Pesanan;
import com.kelompok5.kantin.activity.profil.Profil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import io.grpc.internal.IoUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Beranda extends AppCompatActivity {

    protected Cursor cursor;
    TextView uberanda,mailberanda;
    CircleImageView apa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        uberanda = (TextView) findViewById(R.id.namaberanda);
        mailberanda = (TextView) findViewById(R.id.emailberanda);
        apa = findViewById(R.id.profilberanda);

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
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if(response.code() == 200){
                            try{
                                final JSONObject jsonObject = new JSONObject(response.body().string());
                                Beranda.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try{
                                            uberanda.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("username"));
                                            mailberanda.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("email"));
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
                                                                        apa.setImageBitmap(decodedByte);
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
                                            }
                                        }
                                    }
                                });
                            }catch (JSONException e){
                                e.printStackTrace();
                            }catch(Exception e){
                                e.printStackTrace();
                            }finally{
                                Beranda.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pdig.hide();
                                    }
                                });
                            }

                        }else{
                            Beranda.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pdig.hide();
                                    AlertDialog.Builder hbuild = new AlertDialog.Builder(Beranda.this);
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



        SharedPreferences shf = getSharedPreferences("login", MODE_PRIVATE);

        if(!shf.getBoolean("isLogin", false)){
            Intent intys = new Intent(Beranda.this, LoginActivity.class);
            startActivity(intys);
        }

        final SharedPreferences.Editor shed = shf.edit();

        LinearLayout keluar = (LinearLayout) findViewById(R.id.logout);
        LinearLayout ln = (LinearLayout) findViewById(R.id.profilBtn);
        LinearLayout map= (LinearLayout) findViewById(R.id.maps);
        LinearLayout riwayat= (LinearLayout) findViewById(R.id.historitrx);



        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    new AlertDialog.Builder(Beranda.this).setTitle("Admin").setMessage("Apakah anda yakin untuk keluar dari aplikasi?")
                            .setNegativeButton("Yakin", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    shed.remove("isLogin");
                                    shed.commit();
                                    Intent intys = new Intent(Beranda.this, LoginActivity.class);
                                    startActivity(intys);
                                    Beranda.this.finish();

                                }
                            })
                            .setPositiveButton("Tidak Yakin", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }


            }
        });



        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jBeranda = new Intent(Beranda.this, Profil.class);
                startActivity(jBeranda);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent petaa = new Intent(Beranda.this, Pesanan.class);
                startActivity(petaa);
            }
        });
        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent wayat = new Intent(Beranda.this, Histori.class);
                startActivity(wayat);
            }
        });

    }
}
