package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class NotificationsActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout1, constraintLayout2, constraintLayout3;
    private TextView textView21, textView28, textView22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        constraintLayout1 = findViewById(R.id.constraintLayout1);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        constraintLayout3 = findViewById(R.id.constraintLayout3);

        textView21 = findViewById(R.id.textView21);
        textView28 = findViewById(R.id.textView28);
        textView22 = findViewById(R.id.textView22);



        ImageView imageView_arrow1 = findViewById(R.id.imageView_arrow1);
        imageView_arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationsActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleText(textView21);
            }
        });

        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleText(textView28);
            }
        });

        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleText(textView22);
            }
        });

    }

    private void toggleText(TextView textView) {
        if (textView.getText().toString().equals("Включены")) {
            textView.setText("Выключены");
        } else {
            textView.setText("Включены");
        }
    }
}

