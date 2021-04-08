package com.app.fastprint.ui.main.responseModel;

public class CategoriesModel {
    private String name;
    private int images;

    public CategoriesModel(String name, int images) {
        this.name = name;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
