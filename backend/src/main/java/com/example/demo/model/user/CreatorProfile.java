package com.example.demo.model.user;

import com.example.demo.model.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreatorProfile {
    @Id
    @GeneratedValue
    private Long id;
    private Category category;

    @Column(columnDefinition = "TEXT", length = 600)
    private String description;
    private String pageLink;
    private String profileImage;
    private String causeName;

    @OneToOne(mappedBy = "causeProfile")
    @JsonIgnore
    private UserEntity userEntity;

    public CreatorProfile(Category category, String description, String pageLink, String profileImage, String userName) {
        this.category = category;
        this.description = description;
        this.pageLink = pageLink;
        this.profileImage = profileImage;
        this.causeName = userName;
    }

    public String getPageLink(){
        return pageLink;
    }

    public Category getCategory(){
        return category;
    }

    public String getDescription(){
        return description;
    }

    public String getCauseName() {
        return causeName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public boolean hasCauseStringFragment(String name){
        return causeName.contains(name);
    }

    public boolean hasSameId(long id){
        return id == userEntity.getId();
    }

    public boolean hasSamePageLink(String link){
        return this.pageLink.equals(link);
    }

    public boolean hasSameCategory(Category category){
        return this.category == category;
    }
}
