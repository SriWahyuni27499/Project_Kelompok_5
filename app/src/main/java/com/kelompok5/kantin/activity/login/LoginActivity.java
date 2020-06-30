package com.kelompok5.kantin.activity.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.kelompok5.kantin.R;
import com.kelompok5.kantin.activity.beranda.Beranda;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextUname;
    EditText editTextSandi;
    TextInputLayout textInputLayoutUname;
    TextInputLayout textInputLayoutSandi;

    //Declaration Button
    Button buttonLogin;

    //Declaration SqliteHelper


    boolean isUserExist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //helper

        initCreateAccountTextView();
        direcwhats();
        initViews();

        editTextUname = findViewById(R.id.editTextNim);
        editTextSandi = findViewById(R.id.editTextSandi);
        buttonLogin = findViewById(R.id.buttonLogin);

        /*
            Start
            Buat Ambil preference user
         */

        SharedPreferences spf = getSharedPreferences("login", MODE_PRIVATE);
        boolean isLogin = spf.getBoolean("isLogin", false);

        /*
            End
            Buat Ambil preference user
         */

        //arahin kemana klo udah di sharepreference
        if(isLogin){
            Intent ints = new Intent(this, Beranda.class);
            startActivity(ints);
            this.finish();
        }

        //inputan dah kelar setting buttonya(login edition)
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {
                    RequestBody requestBody = new MultipartBody.Builder()
                                                    .setType(MultipartBody.FORM)
                                                    .addFormDataPart("username", editTextUname.getText().toString())
                                                    .addFormDataPart("password", editTextSandi.getText().toString())
                                                    .build();

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://172.17.100.2/kantin/driver_login").post(requestBody).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder build = new AlertDialog.Builder(LoginActivity.this);
                                    build.setTitle("Gagal").setMessage("Tidak ada jaringan konektivitas").show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                            System.out.println(response.body().string());

                            if(response.code() == 200){
                                //Sharedpref
                                SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
                                SharedPreferences.Editor jalankan = login.edit();

                                jalankan.putBoolean("isLogin", true);

                                jalankan.putString("username", editTextUname.getText().toString());

                                jalankan.commit();

                                Intent pindah = new Intent(LoginActivity.this, Beranda.class);
                                startActivity(pindah);
                                LoginActivity.this.finish();
                            }else{
                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder build = new AlertDialog.Builder(LoginActivity.this);
                                        build.setTitle("Gagal").setMessage("Username atau Password salah").show();
                                    }
                                });
                            }

                        }
                    });
                }
            }
        });

    }



    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextUname = LoginActivity.this.findViewById(R.id.editTextuname);
        editTextSandi = LoginActivity.this.findViewById(R.id.editTextSandi);
        textInputLayoutUname = (TextInputLayout) findViewById(R.id.textInputLayoutuname);
        textInputLayoutSandi = (TextInputLayout) findViewById(R.id.textInputLayoutSandi);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Uname = editTextUname.getText().toString();
        String Password = editTextSandi.getText().toString();


        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutSandi.setError("Please enter valid password!");
        } else {
            if (Password.length() >1) {
                valid = true;
                textInputLayoutSandi.setError(null);
            } else {
                valid = false;
                textInputLayoutSandi.setError("Password is to short!");
            }
        }

        return valid;
    }

    //iki tulisane seng tombol registere
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#0c0099'>Saya tidak punya akun. </font><font color='#0c0099'>Buat akun</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //iki tulisane seng tombol registere
    private void direcwhats() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.directwa);
        textViewCreateAccount.setText(fromHtml("<font color='#0c0099'> Lupa Username atau Sandi? </font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:" + "+6282140337661");
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));

            }
        });
    }

}