package com.uigitdev.loginscreen.akun;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.uigitdev.loginscreen.R;
import com.uigitdev.loginscreen.ViewPagerActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText ID, password;
    private TextView forgot_password_text_view;
    private RelativeLayout login_button;
    private CardView login_button_card_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setType();
        forgotPasswordOnClick();
        loginOnClick();
        inputChange();
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
                if (ID.getText().length() > 0 && password.getText().length() > 0) {
                    Toast.makeText(LoginActivity.this, ID.getText() + " " + password.getText(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, ViewPagerActivity.class));
                }
            }
        });
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
