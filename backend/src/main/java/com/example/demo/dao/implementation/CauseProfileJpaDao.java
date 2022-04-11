package com.example.demo.dao.implementation;

import com.example.demo.model.category.Category;
import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CauseProfileJpaDao extends JpaRepository<CreatorProfile, Long> {

    List<CreatorProfile> findByCauseName(String causeName);

    Optional<CreatorProfile> findById(Long id);

    Optional<CreatorProfile> findByPageLink(String pageLink);

    List<CreatorProfile> findByCategory(Category category);

    Optional<CreatorProfile> findByUserEntity(UserEntity userEntity);

}
