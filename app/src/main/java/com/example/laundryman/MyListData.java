package com.example.laundryman;

public class MyListData {

    public MyListData() {
    }

    private String name,date,phone,id;
    private int upper,lower,small;
    private double price;
    boolean ready,paid;

    public MyListData(String name, String date, String phone, int upper, int lower, int small, double price,boolean ready,boolean paid,String id) {
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.upper = upper;
        this.lower = lower;
        this.small = small;
        this.id = id;
        this.price = price;
        this.ready=ready;
        this.paid=paid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUpper() {
        return upper;
    }

    public void setUpper(int upper) {
        this.upper = upper;
    }

    public int getLower() {
        return lower;
    }

    public void setLower(int lower) {
        this.lower = lower;
    }

    public int getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    /*private String description;
    private int imgId;
    public MyListData(String description, int imgId) {
        this.description = description;
        this.imgId = imgId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }*/
}
