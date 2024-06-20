package com.example.sueno;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LikeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);


        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LikeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


        Button Button3 = findViewById(R.id.button3);
        Button3.setOnClickListener(new View.OnClickListener() {
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