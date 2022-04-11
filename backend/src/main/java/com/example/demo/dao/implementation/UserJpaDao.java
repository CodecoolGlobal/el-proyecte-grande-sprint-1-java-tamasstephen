package com.example.demo.dao.implementation;

import com.example.demo.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    void delete(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);

}
