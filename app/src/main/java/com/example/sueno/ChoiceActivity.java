package com.example.sueno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChoiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        ImageView imageView01 = findViewById(R.id.imageView01);
        TextView textView001 = findViewById(R.id.textView001);
        TextView textView002 = findViewById(R.id.textView002);
        TextView textView003 = findViewById(R.id.textView003);
        TextView textView004 = findViewById(R.id.textView004);
        Button buttonRemove = findViewById(R.id.button);
        Button buttonAdd = findViewById(R.id.button2);

        int menuId = getIntent().getIntExtra("menuId", 0);
        int imageId = getIntent().getIntExtra("imageView01", 0);
        String title = getIntent().getStringExtra("textView01");
        String price = getIntent().getStringExtra("priceTitle");
        String text1 = getIntent().getStringExtra("textView001");
        String text2 = getIntent().getStringExtra("textView002");
        String text3 = getIntent().getStringExtra("textView003");
        String text4 = getIntent().getStringExtra("textView004");

        imageView01.setImageResource(imageId);
        textView001.setText(text1);
        textView002.setText(text2);
        textView003.setText(text3);
        textView004.setText(text4);

        int position = getIntent().getIntExtra("position", -1);

        buttonRemove.setOnClickListener(v -> {
            if (position != -1) {
                Order.orderItems.remove(position);
                Toast.makeText(ChoiceActivity.this, "Товар удален из корзины", Toast.LENGTH_SHORT).show();
                updateCartAndFinish();
            }
        });

        buttonAdd.setOnClickListener(v -> {
            Order.OrderItem newItem = new Order.OrderItem(menuId, imageId, title, price, text1, text2, text3, text4);
            Order.orderItems.add(newItem);
            Toast.makeText(ChoiceActivity.this, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show();
            updateCartAndFinish();
        });
    }

    private void updateCartAndFinish() {
        Intent intent = new Intent(ChoiceActivity.this, CartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
