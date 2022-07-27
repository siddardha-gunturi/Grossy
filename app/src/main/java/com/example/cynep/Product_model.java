package com.example.cynep;

public class Product_model {

    public String name;
    public String image;
    public int price;



    public Product_model(){

    }


    public Product_model(String name, String image,int price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }







    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

