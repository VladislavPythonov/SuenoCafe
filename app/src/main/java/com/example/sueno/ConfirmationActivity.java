package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConfirmationActivity extends AppCompatActivity {
    private Button confirmButton;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Получаем почту из предыдущего экрана
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        // Устанавливаем полученную почту в поле ввода
        EditText emailEditText = findViewById(R.id.email_et);
        emailEditText.setText(email);

        confirmButton = findViewById(R.id.button_cod);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверяем, подтвержден ли email
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    user.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (user.isEmailVerified()) {
                                // Переходим на MainActivity
                                Intent intent = new Intent(ConfirmationActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish(); // Закрываем ConfirmationActivity
                            } else {
                                // Выводим сообщение об ошибке
                                Toast.makeText(ConfirmationActivity.this, "Подтвердите свою почту, прежде чем войти", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    // Выводим сообщение об ошибке
                    Toast.makeText(ConfirmationActivity.this, "Ошибка: пользователь не найден", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Находим ImageView для кнопки назад
        LinearLayout backLayout = findViewById(R.id.back_layout);

        // Устанавливаем обработчик клика
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода к Activity_Registration
                Intent intent = new Intent(ConfirmationActivity.this, RegisterActivity.class);
                // Запускаем Activity_Registration
                startActivity(intent);

                // Завершаем Activity_Confirmation
                finish();
            }
        });
    }
}