package com.example.sueno;

public class TermsDeliveryItem {
    private final int imageResId;
    private final String title;
    private final String text;

    public TermsDeliveryItem(int imageResId, String title, String text) {
        this.imageResId = imageResId;
        this.title = title;
        this.text = text;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}