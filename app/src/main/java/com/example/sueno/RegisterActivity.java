package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueno.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailEt.getText().toString();
                String password = binding.passwordEt.getText().toString();
                String username = binding.usernameEt.getText().toString();

                if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        if (user != null) {
                                            saveUserToDatabase(user.getUid(), username, email);
                                            sendEmailVerification();
                                            Intent intent = new Intent(RegisterActivity.this, ConfirmationActivity.class);
                                            intent.putExtra("email", email);
                                            startActivity(intent);
                                            finish();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Ошибка при создании аккаунта", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        binding.goToLoginActivityTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveUserToDatabase(String userId, String name, String email) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users").child(userId);

        UserProfile userProfile = new UserProfile(name, email, "", "", ""); // Передаем пустые значения для даты рождения, телефона и адреса

        usersRef.setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Данные пользователя успешно сохранены");
                    Toast.makeText(RegisterActivity.this, "Данные пользователя успешно сохранены", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Ошибка при сохранении данных пользователя", task.getException());
                    Toast.makeText(RegisterActivity.this, "Ошибка при сохранении данных пользователя", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Запрос на подтверждение по электронной почте отправлен", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Ошибка при отправке запроса на подтверждение по электронной почте", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}