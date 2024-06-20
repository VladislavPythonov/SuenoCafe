package com.example.sueno;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TermsDeliveryAdapter extends RecyclerView.Adapter<TermsDeliveryAdapter.ViewHolder> {

    private final List<TermsDeliveryItem> items;

    public TermsDeliveryAdapter(List<TermsDeliveryItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_terms_delivery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TermsDeliveryItem item = items.get(position);
        holder.imageView.setImageResource(item.getImageResId());
        holder.textView.setText(item.getText());
        holder.titleTextView.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
        }
    }
}