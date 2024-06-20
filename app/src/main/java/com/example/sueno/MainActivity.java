package com.example.sueno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        ImageView profileButton = findViewById(R.id.imageView12);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        ImageView menuButton = findViewById(R.id.imageView21);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        ImageView stocksButton = findViewById(R.id.imageView22);
        stocksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StocksActivity.class);
                startActivity(intent);
            }
        });

        ImageView cartButton = findViewById(R.id.imageView23);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


        ConstraintLayout clickableLayout = findViewById(R.id.clickableLayout);
        clickableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutCafe.class);
                startActivity(intent);
            }
        });

        ConstraintLayout clickableLayout1 = findViewById(R.id.clickableLayout1);
        clickableLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TermsDelivery.class);
                startActivity(intent);
            }
        });

        ConstraintLayout clickableLayout2 = findViewById(R.id.clickableLayout2);
        clickableLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StocksActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageView27 = findViewById(R.id.imageView27);
        imageView27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем URI с адресом кафе Sueno
                Uri uri = Uri.parse("yandexmaps://maps.yandex.ru/?text=Istra, ulitsa Ryabkina, 4, 143500, кафе Sueno");

                // Создаем Intent для открытия карт
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);

                // Устанавливаем пакет для Яндекс.Карт (необязательно, но можно указать)
                mapIntent.setPackage("ru.yandex.yandexmaps");

                // Запускаем Intent для открытия Яндекс.Карт
                startActivity(mapIntent);
            }
        });




    }

}