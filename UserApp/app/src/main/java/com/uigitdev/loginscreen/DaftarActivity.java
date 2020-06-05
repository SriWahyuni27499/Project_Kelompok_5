package com.uigitdev.loginscreen;

import android.annotation.SuppressLint;
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

public class DaftarActivity extends AppCompatActivity {
    private EditText nama, ID, password;
    private RelativeLayout daftar_button;
    private CardView daftar_button_card_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setType();
        DaftarButtonStyle();
        daftarOnClick();
        inputChange();
    }

    private void setType() {
        nama = findViewById(R.id.InputNama);
        ID = findViewById(R.id.InputID);
        password = findViewById(R.id.InputPassword);
        daftar_button = findViewById(R.id.daftar_button);
        daftar_button_card_view = findViewById(R.id.daftar_button_card_view);
    }

    @SuppressLint("ResourceType")
    private void DaftarButtonStyle() {
        if (nama.getText().length() > 0 && ID.getText().length() > 0 && password.getText().length() > 0) {
            if (!daftar_button.isFocusable()) {
                daftar_button.setFocusable(true);
                daftar_button.setClickable(true);
                daftar_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
                TypedValue outValue = new TypedValue();
                getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                daftar_button.setBackgroundResource(outValue.resourceId);
            }
        } else {
            if (daftar_button.isFocusable()) {
                daftar_button.setFocusable(false);
                daftar_button.setClickable(false);
                daftar_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCardViewBackground)));
                daftar_button.setBackgroundResource(0);
            }
        }
    }

    private void daftarOnClick() {
        daftar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.getText().length() > 0 && ID.getText().length() > 0 && password.getText().length() > 0) {
                    Toast.makeText(DaftarActivity.this, nama.getText() + " " + ID.getText() + " " + password.getText(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void inputChange() {
        nama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                DaftarButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                DaftarButtonStyle();
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
                DaftarButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
