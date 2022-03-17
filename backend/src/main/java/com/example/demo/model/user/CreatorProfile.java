package com.example.demo.model.user;

import java.util.List;

public class CreatorProfile {
    private Category category;
    private List<String> contentUrls;
    private String description;
    private String pageLink;
    private String profileImage;
    private String userName;
    private long userId;

    public CreatorProfile(Category category, List<String> contentUrls, String description, String pageLink, String profileImage, String userName) {
        this.category = category;
        this.contentUrls = contentUrls;
        this.description = description;
        this.pageLink = pageLink;
        this.profileImage = profileImage;
        this.userName = userName;
    }

    public String getPageLink(){
        return pageLink;
    }

    public Category getCategory(){
        return category;
    }

    public List<String> getContentUrls(){
        return contentUrls;
    }

    public long getUserId(){
        return userId;
    }

    public String getDescription(){
        return description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public boolean hasCreatorSameName(String name){
        return name.equals(userName);
    }

    public boolean hasSameId(long id){
        return id == userId;
    }

    public boolean hasSamePageLink(String link){
        return this.pageLink.equals(link);
    }

    public boolean hasSameCategory(Category category){
        return this.category == category;
    }
}
