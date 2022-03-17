package com.example.demo.model.tip;


import java.time.LocalDate;

public class Tip {
    private long id;
    private int amount;
    private long userId;
    private String pageLink;
    private LocalDate date;
    private Comment comment;

    public Comment getComment(){
        return comment;
    }

    public boolean isUsersDonation(long userId){
        return userId == this.userId;
    }

    public boolean isSameId(long userId) {
        return id == userId;
    }

    public boolean hasSameLink(String pageLink){
        return this.pageLink.equals(pageLink);
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPageLink() {
        return pageLink;
    }

    public void setId(long id) {
        this.id = id;
    }
}
