package com.kelompok5.kantin.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompok5.kantin.MainActivity;
import com.kelompok5.kantin.R;
import com.kelompok5.kantin.activity.beranda.Beranda;
import com.kelompok5.kantin.helper.SqliteHelper;

public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextNim;
    EditText editTextSandi;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutNim;
    TextInputLayout textInputLayoutSandi;

    //Declaration Button
    Button buttonLogin;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String Nim = editTextNim.getText().toString();
                    String Sandi = editTextSandi.getText().toString();

                    //Authenticate user
                    boolean currentUser = sqliteHelper.Authenticate(new Login(Nim, Sandi));

                    Log.d("Auth", String.valueOf(currentUser));

                    //Check Authentication is successful or not
                    if (currentUser) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        //User Logged in Successfully Launch You home screen activity
                        Intent intent=new Intent(LoginActivity.this, Beranda.class);
                        startActivity(intent);
                        finish();

                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }

    //this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextNim = (EditText) findViewById(R.id.editTextNim);
        editTextSandi = (EditText) findViewById(R.id.editTextSandi);
        textInputLayoutNim = (TextInputLayout) findViewById(R.id.textInputLayoutNim);
        textInputLayoutSandi = (TextInputLayout) findViewById(R.id.textInputLayoutSandi);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = editTextNim.getText().toString();
        String Password = editTextSandi.getText().toString();

        //Handling validation for Email field
//        if (!android.util.Patterns.U.matches()) {
//            valid = false;
//            textInputLayoutNim.setError("Please enter valid email!");
//        } else {
//            valid = true;
//            textInputLayoutSandi.setError(null);
//        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutSandi.setError("Please enter valid password!");
        } else {
            if (Password.length() > 4) {
                valid = true;
                textInputLayoutSandi.setError(null);
            } else {
                valid = false;
                textInputLayoutSandi.setError("Password is to short!");
            }
        }

        return valid;
    }


}