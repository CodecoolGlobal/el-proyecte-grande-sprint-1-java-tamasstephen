package com.example.demo.service;

import com.example.demo.dao.implementation.CauseProfileJpaDao;
import com.example.demo.model.category.Category;
import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CreatorProfileServiceTest {

//    @Autowired
    @Mock
    CauseProfileJpaDao causeProfileJpaDao;
    private CreatorProfileService creatorProfileService;
    private CreatorProfile profile;
    private CreatorProfile secondProfile;
    private CreatorProfile uniqueProfile;
    private static final long USER_ID = 1;
    private static final long SECOND_USER_ID = 1;
    private static final long UNIQUE_PROFILE_ID = 5;
    private static final long NON_EXISTING_USER_ID = 5;
    private static final Category UNIQUE_CATEGORY = Category.SCIENCE;


    @BeforeEach
    void init(){
        creatorProfileService = new CreatorProfileService(causeProfileJpaDao);
        profile = new CreatorProfile(Category.GAMING,
                "description",
                "a link",
                "myImage.png",
                "username");
        profile.setUserEntity(UserEntity.builder().build());
        secondProfile = new CreatorProfile(Category.VIDEO,
                "description",
                "a link",
                "myImage.png",
                "username");
        secondProfile.setUserEntity(UserEntity.builder().build());
        uniqueProfile = new CreatorProfile(
                UNIQUE_CATEGORY,
                "description",
                "unique_link",
                "myImage.png",
                "username");
        uniqueProfile.setUserEntity(UserEntity.builder().build());
    }


    @Test
    void add_addsNewCreator_creatorAdded(){
        when(causeProfileJpaDao.saveAndFlush(profile)).thenReturn(profile);
        CreatorProfile p = creatorProfileService.add(profile);

        assertNotNull(p);
    }

    @Test
    void get_getCreatorsByName_returnsCreators(){

        when(causeProfileJpaDao.findAll()).thenReturn(List.of(profile, secondProfile));
        List<CreatorProfile> creators = creatorProfileService.get("username");

        assertTrue(creators.size() > 1);
    }

    @Test
    void get_getCreatorById_returnsCreator(){
        when(causeProfileJpaDao.findById((long)1)).thenReturn(Optional.of(profile));

        Optional<CreatorProfile> result = creatorProfileService.get(USER_ID);

        assertTrue(result.isPresent());
    }

    @Test
    void getCreatorPageByLink_returnsCreator_returnsInvalidCreator(){
        when(causeProfileJpaDao.findByPageLink("link")).thenReturn(Optional.empty());

        Optional<CreatorProfile> result = creatorProfileService.getCreatorPageByPageLink("link");

        assertTrue(result.isEmpty());
    }

    @Test
    void getCreatorPageByLink_returnsCreator_returnsValidCreator(){
        when(causeProfileJpaDao.findByPageLink("a link")).thenReturn(Optional.of(profile));

        Optional<CreatorProfile> result = creatorProfileService.getCreatorPageByPageLink("a link");

        assertTrue(result.isPresent());
    }

    @Test
    void getContentByCategory_returnsListWithCreators_returnsList(){
        when(causeProfileJpaDao.findByCategory(Category.GAMING)).thenReturn(List.of(profile));

        List<CreatorProfile> result = creatorProfileService.getContentsByCategory(Category.GAMING);

        assertTrue(result.size() > 0);
    }

    @Test
    void getAll_returnsAllCreators_returnsCreators(){
        when(causeProfileJpaDao.findAll()).thenReturn(List.of(profile, secondProfile));

        List<CreatorProfile> result = creatorProfileService.getAll();

        assertTrue(result.size() > 1);
    }

    @Test
    void isPageLinkUnique_checksIfLinkAlReadyExists_returnsTrue(){
        when(causeProfileJpaDao.findByPageLink("beautiful")).thenReturn(Optional.empty());

        boolean result = creatorProfileService.isPageLinkUnique("beautiful");

        assertTrue(result);
    }


    @Test
    void isPageLinkUnique_checksIfLinkAlReadyExists_returnsFalse(){
        when(causeProfileJpaDao.findByPageLink("a link")).thenReturn(Optional.of(profile));

        boolean result = creatorProfileService.isPageLinkUnique("a link");

        assertFalse(result);
    }

    @Test
    void updateDescription_updatesProfileDescription_descriptionUpdated(){
        when(causeProfileJpaDao.saveAndFlush(profile)).thenReturn(profile);

        creatorProfileService.updateProfileDescription(profile, "desc");

        Mockito.verify(causeProfileJpaDao, atLeastOnce()).saveAndFlush(profile);
    }

    @Test
    void updateTitle_updatesProfileTitle_titleUpdated(){
        when(causeProfileJpaDao.saveAndFlush(profile)).thenReturn(profile);

        creatorProfileService.updateProfileTitle(profile, "title");

        Mockito.verify(causeProfileJpaDao, atLeastOnce()).saveAndFlush(profile);
    }

    @Test
    void updateCreatorProfile_updatesProfile_profileUpdated(){
        when(causeProfileJpaDao.saveAndFlush(profile)).thenReturn(profile);

        creatorProfileService.updateCreatorProfile(profile, secondProfile);

        Mockito.verify(causeProfileJpaDao, atLeastOnce()).saveAndFlush(profile);

    }



}