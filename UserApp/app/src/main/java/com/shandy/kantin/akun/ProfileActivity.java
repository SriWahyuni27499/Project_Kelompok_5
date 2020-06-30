package com.shandy.kantin.akun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shandy.kantin.R;
import com.shandy.kantin.ui.MenuActivity;

public class ProfileActivity extends AppCompatActivity {

    LinearLayout personalinfo, review;
    TextView personalinfobtn, reviewbtn;
    ImageView ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Base);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);

        personalinfo = findViewById(R.id.personalinfo);
        review = findViewById(R.id.review);
        personalinfobtn = findViewById(R.id.personalinfobtn);
        reviewbtn = findViewById(R.id.reviewbtn);
        ic_back = findViewById(R.id.back_icon);
        /*making personal info visible*/
        personalinfo.setVisibility(View.VISIBLE);
        review.setVisibility(View.GONE);


        personalinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.VISIBLE);
                review.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.colorAccent));
                reviewbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MenuActivity.class));
            }
        });

        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.GONE);
                review.setVisibility(View.VISIBLE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                reviewbtn.setTextColor(getResources().getColor(R.color.colorAccent));

            }
        });
    }
}
