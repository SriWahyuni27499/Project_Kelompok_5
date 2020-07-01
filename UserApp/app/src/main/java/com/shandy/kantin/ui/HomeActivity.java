package com.shandy.kantin.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shandy.kantin.R;
import com.shandy.kantin.ui.akun.DaftarActivity;
import com.shandy.kantin.ui.akun.LoginActivity;
import com.shandy.kantin.ui.menu.MenuActivity;

public class HomeActivity extends AppCompatActivity {
    RelativeLayout login_button;
    TextView register_text_view;
    private TampilManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new TampilManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_home);
        setType();
        loginOnClick();
        registerOnClick();
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(HomeActivity.this, MenuActivity.class));
        finish();
    }


    private void setType() {
        login_button = findViewById(R.id.login_button);
        register_text_view = findViewById(R.id.register_text_view);
    }

    private void registerOnClick() {
        register_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DaftarActivity.class));
            }
        });
    }

    private void loginOnClick() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });
    }
}
