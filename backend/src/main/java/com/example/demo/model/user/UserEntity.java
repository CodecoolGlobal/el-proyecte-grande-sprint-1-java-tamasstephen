package com.example.demo.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long userCreatorProfileId;
    private String email;
    private String password;


    public void payUser(){
        //TODO: implement payment
    }

    public boolean hasSameId(long id){
        return this.id == id;
    }

    public void setContent(Long creatorProfileId){
        this.userCreatorProfileId = creatorProfileId;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }

    public boolean isMatchingEmail(String email){
        return email.equals(this.email);
    }

    public boolean isValidPassword(String password) {
        return password.equals(this.password);
    }

    public Long getId() {
        return id;
    }

    public boolean isCreatorProfileAvailable(){
        return userCreatorProfileId != null;
    }

    public String getPassword() {
        return password;
    }
}

