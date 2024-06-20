package com.example.sueno.adapter;

import android.app.Activity;
import android.util.Pair;
import android.view.View;
import android.app.ActivityOptions;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueno.CartActivity;
import com.example.sueno.ChoiceActivity;
import com.example.sueno.InformationActivity;
import com.example.sueno.MenuActivity;
import com.example.sueno.Order;
import com.example.sueno.R;
import com.example.sueno.model.Menu;

import java.util.List;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    Context context;
    List<Menu> menus;

    public MenuAdapter(Context context, List<Menu> menus) {
        this.context = context;
        this.menus = menus;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItems = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(menuItems);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
        Menu currentMenu = menus.get(position);
        int imageId = context.getResources().getIdentifier(currentMenu.getImg(), "drawable", context.getPackageName());
        holder.imageView01.setImageResource(imageId);
        holder.textView01.setText(currentMenu.getTitle());
        holder.priceTitle.setText(currentMenu.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InformationActivity.class);
                intent.putExtra("imageView01", imageId);
                intent.putExtra("textView001", currentMenu.getText());
                intent.putExtra("textView002", currentMenu.getText2());
                intent.putExtra("textView003", currentMenu.getText3());
                intent.putExtra("textView004", currentMenu.getText4());
                intent.putExtra("buttonView01", currentMenu.getButton());
                intent.putExtra("menuId", currentMenu.getId());
                intent.putExtra("textView01", currentMenu.getTitle());
                intent.putExtra("priceTitle", currentMenu.getPrice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public void filterList(List<Menu> filteredList) {
        menus = filteredList;
        notifyDataSetChanged();
    }



    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout01;
        ImageView imageView01;
        TextView textView01;
        TextView priceTitle;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout01 = itemView.findViewById(R.id.constraintLayout01);
            imageView01 = itemView.findViewById(R.id.imageView01);
            textView01 = itemView.findViewById(R.id.textView01);
            priceTitle = itemView.findViewById(R.id.priceTitle);
        }
    }
}