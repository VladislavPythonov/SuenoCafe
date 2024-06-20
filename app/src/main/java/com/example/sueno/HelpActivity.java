package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Находим ImageView
        ImageView imageView_arrow1 = findViewById(R.id.imageView_arrow1);

        // Устанавливаем обработчик клика
        imageView_arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода в MainActivity
                Intent intent = new Intent(HelpActivity.this, ProfileActivity.class);
                // Запускаем MainActivity
                startActivity(intent);
                // Завершаем ProfileActivity
                finish();
            }
        });

        // Находим ConstraintLayout
        ConstraintLayout constraintLayout1 = findViewById(R.id.constraintLayout1);
        //Устанавливаем обработчик клика
        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода в MyProfileActivity
                Intent intent = new Intent(HelpActivity.this, QuestionsActivity.class);
                // Запускаем MyProfileActivity
                startActivity(intent);
            }
        });

        ConstraintLayout constraintLayout2 = findViewById(R.id.constraintLayout2);
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });


    }
}







