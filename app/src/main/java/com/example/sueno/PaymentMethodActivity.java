package com.example.sueno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class PaymentMethodActivity extends Activity {

    private TextView textViewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentmethod);

        textViewB = findViewById(R.id.textViewB);

        View containerCash = findViewById(R.id.containerCash);


        containerCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewB.setText("Оплатить заказ при получении");
            }
        });


        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentMethodActivity.this, MakingOrder.class);
                startActivity(intent);
                finish();
            }
        });


        ConstraintLayout containerCard = findViewById(R.id.containerCard);
        containerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentMethodActivity.this, NewMapActivity.class);
                startActivity(intent);
            }
        });

        ConstraintLayout Container = findViewById(R.id.Container);
        Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentMethodActivity.this, OrderThroughActivity.class);
                startActivity(intent);
            }
        });

    }

}