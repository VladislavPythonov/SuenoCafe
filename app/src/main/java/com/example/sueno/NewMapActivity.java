package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class NewMapActivity extends AppCompatActivity {

    private EditText editTextNumber, editTextMonth, editTextYear, editTextCVV;
    private ImageView imageView3;
    private TextView textView50;
    private ConstraintLayout container;

    private boolean isNumberValid = false;
    private boolean isMonthValid = false;
    private boolean isYearValid = false;
    private boolean isCVVValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmap);

        // Находим все необходимые элементы управления
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextMonth = findViewById(R.id.editTextMonth);
        editTextYear = findViewById(R.id.editTextYear);
        editTextCVV = findViewById(R.id.editTextCVV);
        imageView3 = findViewById(R.id.imageView3);
        textView50 = findViewById(R.id.textView50);
        container = findViewById(R.id.Container);

        // Устанавливаем слушатели для ввода даты, номера карты и CVV
        setupListeners();

        // Устанавливаем слушатель для imageView3
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPaymentMethodActivity();
            }
        });

        // Слушатель для "Узнать больше"
        textView50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Действия при клике на "Узнать больше"
                Toast.makeText(NewMapActivity.this, "Узнать больше", Toast.LENGTH_SHORT).show();
                // Дополнительные действия по вашему усмотрению
            }
        });

        // Listener для editTextNumber
        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Не используется
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Ваша логика проверки длины номера карты (должно быть ровно 16 цифр)
                isNumberValid = s.length() == 16;
                editTextNumber.setError(isNumberValid ? null : "Номер карты должен состоять из 16 цифр");
                checkAllFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Не используется
            }
        });

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNumberValid && isMonthValid && isYearValid && isCVVValid) {
                    Toast.makeText(NewMapActivity.this, "Обработка оплаты", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(NewMapActivity.this, OrderThroughActivity.class);
                            startActivity(intent);
                            Toast.makeText(NewMapActivity.this, "Оплата произведена!", Toast.LENGTH_SHORT).show();
                        }
                    }, 5000); // Задержка 5 секунд
                } else {
                    Toast.makeText(NewMapActivity.this, "Не все поля заполнены", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Установка слушателей для ввода даты и CVV
    private void setupListeners() {
        // Listener для editTextMonth
        editTextMonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Не используется
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Ваша логика проверки формата месяца
                isMonthValid = isValidInput(s.toString());
                editTextMonth.setError(isMonthValid ? null : "Введите две цифры");
                checkAllFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Не используется
            }
        });

        // Listener для editTextYear
        editTextYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Не используется
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Ваша логика проверки формата года
                isYearValid = isValidInput(s.toString());
                editTextYear.setError(isYearValid ? null : "Введите две цифры");
                checkAllFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Не используется
            }
        });

        // Listener для editTextCVV
        editTextCVV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Не используется
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isCVVValid = s.length() == 3;
                if (isCVVValid) {
                    // Заменяем введенные цифры на *
                    editTextCVV.removeTextChangedListener(this); // Удаляем текущий TextWatcher, чтобы избежать бесконечной рекурсии
                    editTextCVV.setText("***");
                    editTextCVV.setSelection(editTextCVV.getText().length()); // Перемещаем курсор в конец текста
                    editTextCVV.addTextChangedListener(this); // Возвращаем TextWatcher
                }
                editTextCVV.setError(isCVVValid ? null : "CVV должен состоять из трех цифр");
                checkAllFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Не используется
            }
        });
    }

    // Функция для проверки формата месяца/года
    private boolean isValidInput(String input) {
        return input.matches("\\d{2}"); // Проверяем, что введено ровно две цифры
    }

    // Проверка всех полей
    private void checkAllFields() {
        // Метод оставлен пустым, но может использоваться для дополнительной логики
    }

    // Навигация к PaymentMethodActivity
    private void navigateToPaymentMethodActivity() {
        Intent intent = new Intent(NewMapActivity.this, PaymentMethodActivity.class);
        startActivity(intent);
        finish();
    }
}
