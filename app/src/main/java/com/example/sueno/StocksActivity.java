package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.appcompat.app.AppCompatActivity;

public class StocksActivity extends AppCompatActivity {

        private TextView textView31;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_stocks);


            textView31 = findViewById(R.id.textView31);
            textView31.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(StocksActivity.this, "У вас 0 чиликоинов", Toast.LENGTH_SHORT).show();
                }
            });




            ImageView imageView20 = findViewById(R.id.imageView20);
            imageView20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StocksActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            ImageView imageView21 = findViewById(R.id.imageView21);

            imageView21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StocksActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            ImageView imageView23 = findViewById(R.id.imageView23);

            imageView23.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StocksActivity.this, CartActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


        }
    }

