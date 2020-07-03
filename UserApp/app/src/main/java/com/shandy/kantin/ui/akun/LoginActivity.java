package com.shandy.kantin.ui.akun;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.shandy.kantin.R;
import com.shandy.kantin.services.DatabaseHelper;
import com.shandy.kantin.ui.menu.MenuActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText ID, password;
    private TextView forgot_password_text_view;
    private RelativeLayout login_button;
    private CardView login_button_card_view;
    String Username = "Shandy123", Password = "Shandy123";
    DatabaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setType();
        forgotPasswordOnClick();
        loginOnClick();
        inputChange();

        MyDB = new DatabaseHelper(this);
        Cursor res = MyDB.LihatData();
        if(res.moveToNext()){
            finish();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
    }

    }

    private void setType() {
        ID = findViewById(R.id.ID);
        password = findViewById(R.id.password);
        forgot_password_text_view = findViewById(R.id.forgot_password_text_view);
        login_button = findViewById(R.id.login_button);
        login_button_card_view = findViewById(R.id.login_button_card_view);
    }

    @SuppressLint("ResourceType")
    private void loginButtonStyle() {
        if (password.getText().length() > 0 && ID.getText().length() > 0) {
            if (!login_button.isFocusable()) {
                login_button.setFocusable(true);
                login_button.setClickable(true);
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
                TypedValue outValue = new TypedValue();
                getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                login_button.setBackgroundResource(outValue.resourceId);
            }
        } else {
            if (login_button.isFocusable()) {
                login_button.setFocusable(false);
                login_button.setClickable(false);
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCardViewBackground)));
                login_button.setBackgroundResource(0);
            }
        }
    }

    private void forgotPasswordOnClick() {
        forgot_password_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, getString(R.string.lupa_password), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loginOnClick() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User = ID.getText().toString();
                String Pass = password.getText().toString();

//                if (ID.getText().length() > 0 && password.getText().length() > 0) {
//                    Toast.makeText(LoginActivity.this, ID.getText() + " " + password.getText(), Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
//                    finish();
//                }

                if(User.equals("") || Pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Form Masih Kosong!", Toast.LENGTH_SHORT).show();
                }else{
                    if(!User.equals(Username) || !Pass.equals(Password)){
                        Toast.makeText(LoginActivity.this, "Username atau Password Salah!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "Berhasil Login!", Toast.LENGTH_SHORT).show();
                        MyDB.SimpanData(User, Pass);
                        finish();
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }
            }
        }
        }
        );
    }

    private void inputChange() {
        ID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                loginButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                loginButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
