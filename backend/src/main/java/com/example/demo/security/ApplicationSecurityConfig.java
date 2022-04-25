package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
                /*.authorizeRequests()
                .antMatchers("/highlighted"
                        , "/creator/support",
                        "/creator/tips/{pageLink}",
                        "/all-creators",
                        "/creator/{pageLink}/image",
                        "/creator/{pageLink}",
                        "/user",
                        "/login").permitAll()
                .anyRequest()
                .authenticated();*/
    }
}
