package com.example.cosahorrosa;

public class MangaItem {

    private final String name;
    private final String description;
    private final String price;
    private final int imageResource;

    public MangaItem(String name, String description, String price, int imageResource) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }
}
