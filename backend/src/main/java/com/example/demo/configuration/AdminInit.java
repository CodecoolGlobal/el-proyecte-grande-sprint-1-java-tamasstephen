package com.example.demo.configuration;

import com.example.demo.model.user.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminInit {

    UserService userService;

    @Autowired
    public AdminInit(UserService userService) {
        this.userService = userService;
    }

    public void initAdmin(){
        if(!userService.isAdminCreated()){
            UserEntity brandNewAdmin = UserEntity
                    .builder()
                    .email("admin@admin.com")
                    .password("admin")
                    .grantedAuthorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                            .build();
            userService.addAdmin(brandNewAdmin);
        }
    }
}
