package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class TermsDelivery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsdelivery);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        List<TermsDeliveryItem> items = new ArrayList<>();
        items.add(new TermsDeliveryItem(R.drawable.image18, "Оплата доставки"," Можно оплатить следующими способами: наличными или различными платежными методами"));
        items.add(new TermsDeliveryItem(R.drawable.image19, "Доставка","Доставка работает по следующему принципу за пределы Истры:\n" +
                "До 7 км – 200₽, от 2000₽ в чеке – бесплатно;\n" +
                "От 7 до 12 км – 300₽, от 3000₽ в чеке – бесплатно;\n" +
                "От 12 до 15 км – 400₽, от 4000₽ в чеке – бесплатно;\n" +
                "От 15 до 20 км – 500₽, от 5000₽ в чеке – бесплатно.\n" +
                "Далее каждый километр +50₽\n" +
                "\n"));
        items.add(new TermsDeliveryItem(R.drawable.image177, "Время","Мы привезем Ваш заказ точно и в указанное время!"));

        TermsDeliveryAdapter adapter = new TermsDeliveryAdapter(items);
        viewPager.setAdapter(adapter);

        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsDelivery.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}