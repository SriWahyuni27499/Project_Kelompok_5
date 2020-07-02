package com.kelompok5.kantin.activity.profil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
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


import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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

class ImageUtil
{
    public static Bitmap convert(String base64Str) throws IllegalArgumentException
    {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String convert(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

}


public class Edit extends AppCompatActivity {

    protected Cursor cursor;

    TextView uuser;
    EditText ssandi,nnama,aalam,eemail,hhp,ffoto;
    Button execution,rrasedo,editpoto;
    CircleImageView imgUpload;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        uuser= (TextView) findViewById(R.id.editviewusername);
        ssandi= (EditText) findViewById(R.id.editpassword);
        nnama = (EditText) findViewById(R.id.editnama);
        aalam= (EditText) findViewById(R.id.editalamat);
        eemail= (EditText) findViewById(R.id.editemail);
        hhp= (EditText) findViewById(R.id.edittele);
//        ffoto= (EditText) findViewById(R.id.editfoto);
//        imgUpload = findViewById(R.id.uplodFoto);

        execution = (Button) findViewById(R.id.gaskan);
        rrasedo = (Button) findViewById(R.id.gajadi);
        editpoto = (Button) findViewById(R.id.btn_upload);


        final ProgressDialog pdig = new ProgressDialog(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pdig.setMessage("Mengambil Data, mohon tunggu...");
                pdig.setTitle("Proses");
                pdig.show();
            }
        });

        Runnable takeData = new Runnable() {
            @Override
            public void run() {
                SharedPreferences username = getSharedPreferences("login", MODE_PRIVATE);
                final String userString = username.getString("username", "");

                RequestBody reqBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("username", userString)
                        .build();
                final OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url("http://66.42.60.70/tanco/driver_info").post(reqBody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if(response.code() == 200){
                            try{
                                final JSONObject jsonObject = new JSONObject(response.body().string());
                                Edit.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try{
                                            uuser.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("username"));
                                            ssandi.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("password"));
                                            nnama.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("nama_driver"));
                                            aalam.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("alamat"));
                                            eemail.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("email"));
                                            hhp.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("no_telephone"));
//                                            ffoto.setText(jsonObject.getJSONArray("data").getJSONObject(0).getString("foto"));
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
//                                        finally{
//                                            try{
//                                                OkHttpClient newClient = new OkHttpClient();
//                                                Request req = new Request.Builder().url("http://172.17.100.2/kantin/assets/fotoprofil/"+userString+"/"+userString+".jpg").build();
//                                                newClient.newCall(req).enqueue(new Callback() {
//                                                    @Override
//                                                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                                                        e.printStackTrace();
//                                                    }
//
//                                                    @Override
//                                                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                                                        if(response.isSuccessful()){
//                                                            if(response.body().contentLength() != 0){
//                                                                InputStream in = response.body().byteStream();
//                                                                byte[] gambar = IoUtils.toByteArray(in);
//                                                                final Bitmap decodedByte = BitmapFactory.decodeByteArray(gambar, 0, gambar.length);
//                                                                runOnUiThread(new Runnable() {
//                                                                    @Override
//                                                                    public void run() {
//                                                                        imgUpload.setImageBitmap(decodedByte);
//                                                                    }
//                                                                });
//                                                            }
//                                                        }else{
//                                                            System.out.println(response.body());
//                                                        }
//                                                    }
//                                                });
//                                            }catch(Exception e){
//                                                e.printStackTrace();
//                                            }
//                                        }
                                    }
                                });
                            }catch (JSONException e){
                                e.printStackTrace();
                            }catch(Exception e){
                                e.printStackTrace();
                            }finally{
                                Edit.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pdig.hide();
                                    }
                                });
                            }

                        }else{
                            Edit.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pdig.hide();
                                    AlertDialog.Builder hbuild = new AlertDialog.Builder(Edit.this);
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

        Thread takeDatas = new Thread(takeData);
        takeDatas.start();

        execution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {    new AlertDialog.Builder(Edit.this).setTitle("Admin").setMessage("Apakah anda yakin untuk mengubah data diri anda?")
                            .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try{


                                        final ProgressDialog pdig = new ProgressDialog(Edit.this);
                                        pdig.setTitle("Mengupload");
                                        pdig.setMessage("Sedang mengubah data...");
                                        pdig.show();

                                        SharedPreferences username = getSharedPreferences("login", MODE_PRIVATE);
                                        String userString = username.getString("username", "");



                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            jsonObject.put("username", userString);
                                            jsonObject.put("password", ssandi.getText().toString());
                                            jsonObject.put("nama_driver", nnama.getText().toString());
                                            jsonObject.put("alamat", aalam.getText().toString());
                                            jsonObject.put("email", eemail.getText().toString());
                                            jsonObject.put("no_telephone", hhp.getText().toString());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        RequestBody reqBody = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json; charset=utf-8"));
                                        OkHttpClient okHttpClient = new OkHttpClient();
                                        Request request = new Request.Builder().url("http://66.42.60.70/tanco/driver").put(reqBody).build();
                                        okHttpClient.newCall(request).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                                if(response.code() == 200){
                                                    Edit.this.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            pdig.hide();
                                                            AlertDialog.Builder abuild = new AlertDialog.Builder(Edit.this);
                                                            abuild.setTitle("Berhasil").setMessage("Berhasil mengubah data").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.dismiss();
                                                                    Intent neo = new Intent(Edit.this, Profil.class);
                                                                    startActivity(neo);
                                                                    Edit.this.finish();
                                                                }
                                                            }).show();
                                                        }
                                                    });
                                                }else{
                                                    System.out.println(response.body().string());
                                                }
                                            }
                                        });



                                    }catch(Exception e){

                                    }

                                }
                            })
                            .setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

//                                    dialog.dismiss();
                                    Intent p = new Intent(Edit.this, Profil.class);
                                    startActivity(p);

                                }
                            })
                            .show();
                }
            }
        });

        rrasedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keprofil = new Intent(Edit.this,Profil.class);
                startActivity(keprofil);
            }
        });

        editpoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Edit.this).setTitle("Costumer Service").setMessage("Untuk mengubah foto driver, silahkan menghubungi Customer Service")
                                                    .setNegativeButton("Hubungi CS", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                            try{
                                                                Uri uri = Uri.parse("smsto:" + "+6282140337661");
                                                                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                                                                i.setPackage("com.whatsapp");
                                                                startActivity(Intent.createChooser(i, ""));
                                                            }catch(Exception e){

                                                            }

                                                        }
                                                    })
                                                    .setPositiveButton("Urungkan", new DialogInterface.OnClickListener() {
                                                        @Override

                                                        public void onClick(DialogInterface dialog, int which) {

                                                            dialog.dismiss();

                                                        }
                                                    })
                                                    .show();
            }
        });


    }
}
