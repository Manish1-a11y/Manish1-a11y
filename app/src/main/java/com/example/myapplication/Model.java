package com.example.myapplication;

public class Model {

    String details, price, Address;
    String photo;

    public Model() {
    }

    public Model(String details, String price, String address, String photo) {
        this.details = details;
        this.price = price;
        Address = address;
        this.photo = photo;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
