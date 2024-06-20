package com.example.sueno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MakingOrder extends AppCompatActivity {

    private EditText editTextAdsress, editTextTime, editTextComment;
    private ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makingorder);


        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakingOrder.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Инициализация EditText и контейнера
        editTextAdsress = findViewById(R.id.editTextAdsress);
        editTextTime = findViewById(R.id.editTextTime);
        editTextComment = findViewById(R.id.editTextComment);
        container = findViewById(R.id.Container);

        // Установка обработчика клика на контейнер "Готово"
        container.setOnClickListener(v -> {
            if (validateFields()) {
                // Поля заполнены, переходим в PaymentMethodActivity
                Intent intent = new Intent(MakingOrder.this, PaymentMethodActivity.class);
                startActivity(intent);
            } else {
                // Поля не заполнены, показать пользователю сообщение или другую обратную связь
                Toast.makeText(MakingOrder.this, "Пожалуйста, заполните все обязательные поля", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Метод для проверки заполненности полей
    private boolean validateFields() {
        String address = editTextAdsress.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();

        // Проверяем, что все поля не пустые
        return !address.isEmpty() && !time.isEmpty();
    }
}