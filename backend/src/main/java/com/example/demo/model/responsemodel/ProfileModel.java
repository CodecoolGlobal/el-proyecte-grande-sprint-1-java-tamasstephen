package com.example.demo.model.responsemodel;

import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileModel {

    private UserEntity userEntity;
    private CreatorProfile profile;

}
