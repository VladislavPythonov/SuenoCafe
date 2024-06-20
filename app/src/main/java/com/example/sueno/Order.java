package com.example.sueno;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public static List<OrderItem> orderItems = new ArrayList<>();

    public static class OrderItem {
        private int id;
        private int imageId;
        private String title;
        private String price;
        private String text1;
        private String text2;
        private String text3;
        private String text4;

        public OrderItem(int id, int imageId, String title, String price, String text1, String text2, String text3, String text4) {
            this.id = id;
            this.imageId = imageId;
            this.title = title;
            this.price = price;
            this.text1 = text1;
            this.text2 = text2;
            this.text3 = text3;
            this.text4 = text4;
        }

        public int getId() {
            return id;
        }
        public int getImageId() {
            return imageId;
        }

        public String getTitle() {
            return title;
        }
        public String getPrice() {
            return price;
        }
        public String getText1() {
            return text1;
        }

        public String getText2() {
            return text2;
        }

        public String getText3() {
            return text3;
        }

        public String getText4() {
            return text4;
        }
    }
}