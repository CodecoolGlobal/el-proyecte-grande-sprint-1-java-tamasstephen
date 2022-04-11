package com.example.demo.dao.implementation;

import com.example.demo.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaDao extends JpaRepository<UserEntity, Long> {
}
