package com.example.demo.service;

import com.example.demo.dao.implementation.UserJpaDao;
import com.example.demo.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class UserService implements UserDetailsService {

    private final UserJpaDao userDao;

    @Autowired
    public UserService(UserJpaDao userDao) {
        this.userDao = userDao;
    }

    public UserEntity add(UserEntity userEntity){
        userEntity.setGrantedAuthorities(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        UserEntity user = userDao.save(userEntity);
        return user;
    }

    public UserEntity addAdmin(UserEntity userEntity){
        userEntity.setGrantedAuthorities(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserEntity user = userDao.save(userEntity);
        return user;
    }

    public List<UserEntity> getAllUsers(){
        return userDao.findAll();
    }

    public Optional<UserEntity> getUser(long id){
        return userDao.findById(id);
    }

    public boolean isEmailAvailable(String email){
        return userDao.findByEmail(email).isEmpty();
    }

    public Optional<UserEntity> getUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    public void deleteUser(UserEntity userEntity){
        userDao.delete(userEntity);
    }

    public void updateUser(UserEntity prevUserEntity, UserEntity newUserEntity){
        prevUserEntity.setEmail(newUserEntity.getEmail());
        prevUserEntity.setPassword(newUserEntity.getPassword());
        if (newUserEntity.getCauseProfile() != null){
            prevUserEntity.setCauseProfile(newUserEntity.getCauseProfile());
        }
        userDao.save(prevUserEntity);
    }

    public void updateEmail(UserEntity prevUserEntity, String email){
        UserEntity newUserEntity = UserEntity.builder()
                .email(email)
                .causeProfile(prevUserEntity.getCauseProfile())
                .password(prevUserEntity.getPassword())
                .id(prevUserEntity.getId())
                .build();
        updateUser(prevUserEntity, newUserEntity);
    }

    public List<String> getAllCustomers(){
        return userDao.findByGrantedAuthorities(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
                .stream().map(UserEntity::getEmail).collect(Collectors.toList());
    }

    public boolean isAdminCreated(){
        return userDao.findAll().stream().anyMatch(UserEntity::isAdmin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByEmail(username).orElse(null);
    }
}
