package com.example.demo.service;

import com.example.demo.dao.TipDao;
import com.example.demo.dao.implementation.TipJpaDao;
import com.example.demo.model.tip.Comment;
import com.example.demo.model.tip.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TipService {

    private final TipJpaDao tipJpaDao;

    @Autowired
    public TipService(TipJpaDao tipJpaDao) {
        this.tipJpaDao = tipJpaDao;
    }

    public void add(Tip tip) {
        tipJpaDao.save(tip);
    }

    public List<Tip> getCommentsByPageLink(String pageLink){
        return tipJpaDao.findTipsByPageLink(pageLink);
    }

    public List<Tip> getAll() {
        return tipJpaDao.findAll();
    }

}
