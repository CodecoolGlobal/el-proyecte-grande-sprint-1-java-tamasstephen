package com.example.demo.service;

import com.example.demo.model.category.Category;
import com.example.demo.model.user.CreatorProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreatorProfileServiceTest {

    @Autowired
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
        profile = new CreatorProfile(Category.GAMING,
                List.of("first", "second"),
                "description",
                "a link",
                "myImage.png",
                "username");
        profile.setUserId(USER_ID);
        secondProfile = new CreatorProfile(Category.VIDEO,
                List.of("first", "second"),
                "description",
                "a link",
                "myImage.png",
                "username");
        secondProfile.setUserId(SECOND_USER_ID);
        uniqueProfile = new CreatorProfile(
                UNIQUE_CATEGORY,
                List.of("first", "second"),
                "description",
                "unique_link",
                "myImage.png",
                "username");
        uniqueProfile.setUserId(UNIQUE_PROFILE_ID);
    }


    @Test
    void add_addsNewCreator_creatorAdded(){
        creatorProfileService.add(profile);

        assertEquals(creatorProfileService.get(USER_ID).get().getUserId(), USER_ID);
    }

    @Test
    void get_getCreatorsByName_returnsCreators(){
        creatorProfileService.add(profile);
        creatorProfileService.add(secondProfile);

        List<CreatorProfile> creators = creatorProfileService.get("username");

        assertTrue(creators.size() > 1);
    }

    @Test
    void get_getCreatorsByName_doesNotReturnCreator(){
        creatorProfileService.add(profile);
        creatorProfileService.add(secondProfile);

        List<CreatorProfile> creators = creatorProfileService.get("hey");

        assertTrue(creators.size() < 1);
    }

    @Test
    void get_getCreatorByUserId_returnsCreator(){
        creatorProfileService.add(profile);

        Optional<CreatorProfile> result = creatorProfileService.get(USER_ID);

        assertTrue(result.isPresent());
    }

    @Test
    void get_getCreatorByUserId_returnsNothing(){

        Optional<CreatorProfile> result = creatorProfileService.get(NON_EXISTING_USER_ID);

        assertTrue(result.isEmpty());
    }

    @Test
    void getCreatorPageByLink_returnsCreator_returnsInvalidCreator(){
        creatorProfileService.add(profile);

        Optional<CreatorProfile> result = creatorProfileService.getCreatorPageByPageLink("link");

        assertTrue(result.isEmpty());
    }

    @Test
    void getCreatorPageByLink_returnsCreator_returnsValidCreator(){
        creatorProfileService.add(profile);

        Optional<CreatorProfile> result = creatorProfileService.getCreatorPageByPageLink("a link");

        assertTrue(result.isPresent());
    }

    @Test
    void getContentByCategory_returnsListWithCreators_returnsList(){
        creatorProfileService.add(profile);

        List<CreatorProfile> result = creatorProfileService.getContentsByCategory(Category.GAMING);

        assertTrue(result.size() > 0);
    }

    @Test
    void getContentByCategory_returnsListWithCreators_returnsNothing(){
        creatorProfileService.add(profile);

        List<CreatorProfile> result = creatorProfileService.getContentsByCategory(Category.SCIENCE);

        assertFalse(result.size() > 0);
    }

    @Test
    void getAll_returnsAllCreators_returnsCreators(){
        creatorProfileService.add(profile);
        creatorProfileService.add(secondProfile);

        List<CreatorProfile> result = creatorProfileService.getAll();

        assertTrue(result.size() > 1);
    }

    @Test
    void isPageLinkUnique_checksIfLinkAlReadyExists_returnsTrue(){
        creatorProfileService.add(profile);

        boolean result = creatorProfileService.isPageLinkUnique("beautiful");

        assertTrue(result);
    }


    @Test
    void isPageLinkUnique_checksIfLinkAlReadyExists_returnsFalse(){
        creatorProfileService.add(profile);

        boolean result = creatorProfileService.isPageLinkUnique("a link");

        assertFalse(result);
    }

    @Test
    void updateCreatorProfile_replacesCreatorDetailsWithNewOnes_changesSuccessFull(){
        creatorProfileService.add(uniqueProfile);
        creatorProfileService.updateCreatorProfile(uniqueProfile, profile);

        CreatorProfile result = creatorProfileService.get(UNIQUE_PROFILE_ID).get();

        assertNotSame(result.getCategory(), UNIQUE_CATEGORY);
    }



}