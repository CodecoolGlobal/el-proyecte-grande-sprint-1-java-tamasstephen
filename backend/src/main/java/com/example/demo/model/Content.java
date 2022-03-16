package com.example.demo.model;

import java.util.List;

public class Content {
    private Category category;
    private List<String> contentUrls;
    private String description;
    private String pageLink;

    public String getPageLink(){
        return pageLink;
    }

    public Category getCategory(){
        return category;
    }

}
