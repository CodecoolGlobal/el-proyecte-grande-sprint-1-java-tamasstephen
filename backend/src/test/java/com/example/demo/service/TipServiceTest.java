package com.example.demo.service;

import com.example.demo.dao.implementation.TipJpaDao;
import com.example.demo.model.tip.Tip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TipServiceTest {

    @Mock
    TipJpaDao tipJpaDao;

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

        tipService = new TipService(tipJpaDao);
    }

    @Test
    void add_addsNewTipToTips_AddsTip(){
        when(tipJpaDao.save(firstTip)).thenReturn(firstTip);

        tipService.add(firstTip);

        verify(tipJpaDao, atLeastOnce()).save(firstTip);
    }

    @Test
    void getCommentsByPageLink_returnsComments_returnsValidNumberOfComments(){
        when(tipJpaDao.findTipsByPageLink(MOST_POPULAR)).thenReturn(List.of(firstTip, secondTip));

        List<Tip> result = tipService.getCommentsByPageLink(MOST_POPULAR);

        assertEquals(result.size(),  2);
    }

    @Test
    void getAll_returnsAllTips_returnsTips(){
        when(tipJpaDao.findAll()).thenReturn(List.of(firstTip, secondTip, thirdTip));

        List<Tip> result = tipService.getAll();

        assertEquals(result.size(), 3);
    }
}