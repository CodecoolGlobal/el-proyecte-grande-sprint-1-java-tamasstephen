package com.example.demo.model.responsemodel;

import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileModel {

    private User user;
    private CreatorProfile profile;

}
