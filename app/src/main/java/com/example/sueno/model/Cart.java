package com.example.sueno.model;

public class Cart {

    int id;
    String img, title, price;


    public Cart(int id, String img, String title, String price) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.price = price;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}