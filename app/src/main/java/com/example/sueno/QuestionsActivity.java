package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // Находим ImageView
        ImageView imageView3 = findViewById(R.id.imageView3);

        // Устанавливаем обработчик клика
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода в MainActivity
                Intent intent = new Intent(QuestionsActivity.this, HelpActivity.class);
                // Запускаем MainActivity
                startActivity(intent);
                // Завершаем ProfileActivity
                finish();
            }
        });
    }
}