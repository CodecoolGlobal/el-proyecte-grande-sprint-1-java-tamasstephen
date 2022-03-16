package com.example.demo.model;


import java.time.LocalDate;

public class Donation {
    private long id;
    private int amount;
    private long userId;
    private LocalDate date;
    private Comment comment;

    public Comment getComment(){
        return comment;
    }

    public boolean isUsersDonation(long userId){
        return userId == this.userId;
    }


}
