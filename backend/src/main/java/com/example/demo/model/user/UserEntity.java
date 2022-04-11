package com.example.demo.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn (name="profile_id")
    @JsonIgnore
    private CreatorProfile causeProfile;

    private String email;
    private String password;


    public void payUser(){
        //TODO: implement payment
    }

    public boolean hasSameId(long id){
        return this.id == id;
    }

    public void setContent(CreatorProfile causeProfile){
        this.causeProfile = causeProfile;
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

    public boolean isCreatorProfileAvailable(){
        return causeProfile != null;
    }

}

