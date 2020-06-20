package com.kelompok5.kantin.activity.beranda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.kelompok5.kantin.R;
import com.kelompok5.kantin.activity.login.Login;
import com.kelompok5.kantin.activity.login.LoginActivity;
import com.kelompok5.kantin.activity.profil.Profil;

public class Beranda extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        SharedPreferences shf = getSharedPreferences("login", MODE_PRIVATE);

        if(!shf.getBoolean("isLogin", false)){
            Intent intys = new Intent(Beranda.this, LoginActivity.class);
            startActivity(intys);
        }

        final SharedPreferences.Editor shed = shf.edit();

        Button btnLogout = (Button) findViewById(R.id.logout);
        LinearLayout ln = (LinearLayout) findViewById(R.id.profilBtn);
        CardView peta = (CardView) findViewById(R.id.maps);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shed.remove("isLogin");
                shed.commit();
                Intent intys = new Intent(Beranda.this, LoginActivity.class);
                startActivity(intys);
                Beranda.this.finish();
            }
        });



        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jBeranda = new Intent(Beranda.this, Profil.class);
                startActivity(jBeranda);
            }
        });

        peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent petaa = new Intent(Beranda.this, com.kelompok5.kantin.Map.class);
                startActivity(petaa);
            }
        });

    }
}
