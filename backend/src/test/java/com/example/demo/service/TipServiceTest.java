package com.example.demo.service;

import com.example.demo.model.tip.Tip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TipServiceTest {

    @Autowired
    private TipService tipService;

    private Tip firstTip;
    private Tip secondTip;
    private Tip thirdTip;

    private final static String POPULAR_SITE = "GeorgeLucas";
    private final static String MOST_POPULAR = "stephaniecombs";

    @BeforeEach
    void init(){
        firstTip = new Tip(10,
                POPULAR_SITE,
                "Peter Parker",
                "Great job");
        secondTip = new Tip(10,
                MOST_POPULAR,
                "Georg Lucas",
                "Well done");
        thirdTip = new Tip(10,
                MOST_POPULAR,
                "Georg Lucas",
                "I am great!");
    }

    @Test
    void add_addsNewTipToTips_AddsTip(){
        firstTip.setUserId(1);
        tipService.add(firstTip);

        List<Tip> result = tipService.getCommentsByPageLink(POPULAR_SITE);

        assertTrue(result.size() > 0);
    }

    @Test
    void getCommentsByPageLink_returnsComments_returnsValidNumberOfComments(){

        secondTip.setUserId(2);
        tipService.add(secondTip);
        thirdTip.setUserId(3);
        tipService.add(thirdTip);

        List<Tip> result = tipService.getCommentsByPageLink(MOST_POPULAR);

        assertEquals(result.size(),  2);

    }
}