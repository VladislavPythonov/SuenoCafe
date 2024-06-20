package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ConstraintLayout balanceContainer = findViewById(R.id.balanceContainer);
        balanceContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, Bonuses.class);
                startActivity(intent);
            }
        });

        ConstraintLayout constraintLayout1 = findViewById(R.id.constraintLayout1);
        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MyProfileActivity.class);
                startActivity(intent);
            }
        });

        ConstraintLayout constraintLayout2 = findViewById(R.id.constraintLayout2);
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        ConstraintLayout constraintLayout3 = findViewById(R.id.constraintLayout3);
        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EstimationActivity.class);
                startActivity(intent);
            }
        });

        ConstraintLayout constraintLayout4 = findViewById(R.id.constraintLayout4);
        constraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, AboutAppActivity.class);
                startActivity(intent);
            }
        });

        ConstraintLayout constraintLayout5 = findViewById(R.id.constraintLayout5);
        constraintLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });

    }
}

