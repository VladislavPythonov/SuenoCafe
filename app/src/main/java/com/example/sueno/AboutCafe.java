package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;
import java.util.List;

public class AboutCafe extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutcafe);

        viewPager = findViewById(R.id.viewPager);

        List<Integer> images = Arrays.asList(
                R.drawable.image55,
                R.drawable.image11,
                R.drawable.image22,
                R.drawable.image33,
                R.drawable.image44,
                R.drawable.image66
        );

        AboutCafeAdapter adapter = new AboutCafeAdapter(images);
        viewPager.setAdapter(adapter);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = (currentItem == adapter.getItemCount() - 1) ? 0 : currentItem + 1;
                viewPager.setCurrentItem(nextItem, true);
                handler.postDelayed(this, 3000); // Change image every 3 seconds
            }
        };
        handler.postDelayed(runnable, 3000);

        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutCafe.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
