package com.example.sueno.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sueno.R;
import com.example.sueno.model.Cart;
import java.util.List;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private List<Cart> carts;

    public CartAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int position) {
        return carts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
            holder = new ViewHolder();
            holder.imageView01 = convertView.findViewById(R.id.imageView01);
            holder.textView01 = convertView.findViewById(R.id.textView01);
            holder.priceTitle = convertView.findViewById(R.id.priceTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cart cartItem = carts.get(position);
        holder.textView01.setText(cartItem.getTitle());
        holder.priceTitle.setText(cartItem.getPrice());

        // Установка изображения товара в корзине
        int imageId = context.getResources().getIdentifier(cartItem.getImg(), "drawable", context.getPackageName());
        holder.imageView01.setImageResource(imageId);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView01;
        TextView textView01;
        TextView priceTitle;
    }
}