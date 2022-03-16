package com.example.demo.model.user;

public enum Category {
    VIDEO("video"),
    MUSIC("music"),
    DEVELOPMENT("development"),
    GAMING("gaming"),
    SCIENCE("science");

    private String category;

    Category(String category) {
        this.category = category;
    }

    @Override
    public String toString(){
        return category;
    }
}
