package com.example.cynep;

public class Category_model {

        public String name;
        public String image;

        public Category_model(){

        }
        public Category_model(String name, String image) {
            this.name = name;
            this.image = image;
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
    }

