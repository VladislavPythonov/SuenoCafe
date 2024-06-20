package com.example.sueno;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderThroughActivity extends AppCompatActivity {

    private ListView ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderthrough);

        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderThroughActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView imageView56 = findViewById(R.id.imageView56);
        imageView56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderThroughActivity.this, CartActivity.class);
                intent.putExtra("clearCart", true);
                startActivity(intent);
                finish();
            }
        });

        ordersList = findViewById(R.id.orders_list1);
        List<String> orderedItems = getCartData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderedItems);
        ordersList.setAdapter(adapter);
    }

    private List<String> getCartData() {
        SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
        Set<String> orderedItemsSet = sharedPreferences.getStringSet("orderedItems", new HashSet<>());
        return new ArrayList<>(orderedItemsSet);
    }

    private void clearCartInCartActivity() {
        SharedPreferences sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}