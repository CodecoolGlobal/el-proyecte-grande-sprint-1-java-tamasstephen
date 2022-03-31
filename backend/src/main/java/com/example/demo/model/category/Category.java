package com.example.demo.model.category;

public enum Category {
    VIDEO("video"),
    MUSIC("music"),
    DEVELOPMENT("development"),
    GAMING("gaming"),
    SCIENCE("science"),
    CHARITY("charity"),
    ECOLOGY("ecology"),
    OTHER("other");

    private String category;

    Category(String category) {
        this.category = category;
    }

    @Override
    public String toString(){
        return category;
    }
}
