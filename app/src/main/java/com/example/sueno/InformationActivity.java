package com.example.sueno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

public class InformationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        ImageView imageView01 = findViewById(R.id.imageView01);
        TextView textView001 = findViewById(R.id.textView001);
        TextView textView002 = findViewById(R.id.textView002);
        TextView textView003 = findViewById(R.id.textView003);
        TextView textView004 = findViewById(R.id.textView004);
        Button buttonView01 = findViewById(R.id.buttonView01);

        // Получаем данные из Intent
        Intent intent = getIntent();
        int imageId = intent.getIntExtra("imageView01", 0);
        String text1 = intent.getStringExtra("textView001");
        String text2 = intent.getStringExtra("textView002");
        String text3 = intent.getStringExtra("textView003");
        String text4 = intent.getStringExtra("textView004");
        String price = intent.getStringExtra("priceTitle");

        // Устанавливаем данные в соответствующие View
        imageView01.setImageResource(imageId);
        textView001.setText(text1 != null ? text1 : "");
        textView002.setText(text2 != null ? text2 : "");
        textView003.setText(text3 != null ? text3 : "");
        textView004.setText(text4 != null ? text4 : "");


        if (price != null) {
            buttonView01.setText("В корзину " + price);
        } else {
            buttonView01.setText("В корзину"); // В случае, если цена не передана, можно установить стандартный текст
        }


        // Обработчик кнопки добавления в корзину
        buttonView01.setOnClickListener(v -> addToCart());
    }

    private void addToCart() {
        Intent intent = getIntent();
        int menuId = intent.getIntExtra("menuId", 0);
        int imageId = intent.getIntExtra("imageView01", 0);
        String title = intent.getStringExtra("textView01");
        String price = intent.getStringExtra("priceTitle");
        String text1 = intent.getStringExtra("textView001");
        String text2 = intent.getStringExtra("textView002");
        String text3 = intent.getStringExtra("textView003");
        String text4 = intent.getStringExtra("textView004");

        // Создаем новый элемент заказа и добавляем его в список
        Order.OrderItem newItem = new Order.OrderItem(menuId, imageId, title, price, text1, text2, text3, text4);
        Order.orderItems.add(newItem);


        Toast.makeText(getApplicationContext(), "Блюдо добавлено в корзину!", Toast.LENGTH_SHORT).show();
    }
}
