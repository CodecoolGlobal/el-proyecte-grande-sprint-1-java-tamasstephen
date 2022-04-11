package com.example.demo.model.tip;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tip {
    @Id
    @GeneratedValue
    private long id;
    private int amount;
    private long userId;
    private String pageLink;
    private LocalDate date;
    private String supporter;
    private String comment;

    public Tip(int amount, String pageLink, String supporter, String comment) {
        this.amount = amount;
        this.pageLink = pageLink;
        this.supporter = supporter;
        this.comment = comment;
    }

    public int getAmount() {
        return amount;
    }

    public String getSupporter() {
        return supporter;
    }

    public String getComment() {
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
