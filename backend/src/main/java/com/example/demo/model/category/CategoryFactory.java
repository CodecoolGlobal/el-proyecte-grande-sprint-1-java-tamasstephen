package com.example.demo.model.category;

public class CategoryFactory {


    public static Category getCategoryByString(String categoryAsString){
        switch (categoryAsString) {
            case "charity":
                return Category.CHARITY;
            case "ecology":
                return Category.ECOLOGY;
            case "science":
                return Category.SCIENCE;
            case "music":
                return Category.MUSIC;
        }
        return Category.OTHER;
    }
}
