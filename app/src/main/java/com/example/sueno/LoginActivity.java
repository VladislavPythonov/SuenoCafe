package com.example.sueno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sueno.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.os.Handler;
import android.os.Looper;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private int failedAttempts = 0;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Обработчик клика для кнопки "Войти"
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверка заполненности полей электронной почты и пароля
                String email = binding.emailEt.getText().toString();
                String password = binding.passwordEt.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                } else {
                    // Проверяем, не превышено ли количество неудачных попыток
                    if (failedAttempts >= 3 && !isTimerRunning) {
                        startTimer();
                        return;
                    }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        if (user != null && user.isEmailVerified()) {
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Подтвердите свою почту, прежде чем войти", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        failedAttempts++;

                                        String errorMessage = task.getException().getMessage();
                                        if (errorMessage != null) {
                                            Toast.makeText(getApplicationContext(), "Ошибка: " + errorMessage, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Произошла ошибка входа", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                }
            }
        });

        // Обработчик клика для кнопки "Создать"
        binding.goToRegisterActivityTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переходим на RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    // Метод для запуска таймера на 1 минуту
    private void startTimer() {
        isTimerRunning = true;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                failedAttempts = 0;
                isTimerRunning = false;
            }
        }, 60000); // 60000 миллисекунд = 1 минута
        Toast.makeText(getApplicationContext(), "Слишком много неудачных попыток. Попробуйте позже.", Toast.LENGTH_SHORT).show();
    }
}