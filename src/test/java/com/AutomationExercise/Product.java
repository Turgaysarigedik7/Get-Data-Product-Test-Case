package com.AutomationExercise;

import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class Product implements IObjectFeatures {

    private String name;

    String category;
    String subCategory;
    String price;
    String link;
    ImageData image;

    public Product(String _name, String _price, String _link,ImageData _image,String _category, String _subCategory){
        name = _name;
        price = _price;
        link = _link;
        image = _image;
        category = _category;
        subCategory = _subCategory;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getSubCategory(){
        return subCategory;
    }
    public void setSubCategory(String subCategory){
        this.subCategory = subCategory;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public ImageData getImage(){
        return image;
    }
    public void setImage(ImageData image){
        this.image = image;
    }
    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link = link;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
      this.name = name;
    }
}
