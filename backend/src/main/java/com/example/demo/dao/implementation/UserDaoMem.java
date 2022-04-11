package com.example.demo.dao.implementation;

import com.example.demo.dao.UserDao;
import com.example.demo.model.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userMem")
public class UserDaoMem implements UserDao {

    private static final ArrayList<UserEntity> USER_ENTITIES = new ArrayList<>();

    @Override
    public List<UserEntity> get(String email) {
        return USER_ENTITIES.stream().filter(user -> user.getEmail().equals(email)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> get(long id) {
        return USER_ENTITIES.stream().filter(user -> user.hasSameId(id)).findFirst();
    }


    @Override
    public void add(UserEntity userEntity) {
        long id = USER_ENTITIES.size() + 1;
        userEntity.setId(id);
        USER_ENTITIES.add(userEntity);
    }

    @Override
    public List<UserEntity> getAll() {
        return USER_ENTITIES;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return USER_ENTITIES.stream().filter(user -> user.isMatchingEmail(email)).findFirst().isEmpty();
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return USER_ENTITIES.stream().filter(user -> user.isMatchingEmail(email)).findFirst();
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        Optional<UserEntity> userOptional = getUserByEmail(userEntity.getEmail());
        if (userOptional.isPresent() && userOptional.get().isValidPassword(userEntity.getPassword())){
            USER_ENTITIES.remove(userOptional.get());
        }
    }

    @Override
    public void update(UserEntity prevUserEntity, UserEntity nextUserEntity){
        long id = prevUserEntity.getId();
        nextUserEntity.setId(id);
        USER_ENTITIES.remove(prevUserEntity);
        USER_ENTITIES.add(nextUserEntity);
    }


}
