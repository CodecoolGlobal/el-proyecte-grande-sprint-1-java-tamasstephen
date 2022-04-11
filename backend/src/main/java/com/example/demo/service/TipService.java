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
//        tipDao.add(tip);
    }

    public List<Tip> getCommentsByPageLink(String pageLink){
        return tipJpaDao.findTipsByPageLink(pageLink);
//        return tipDao.getCommentsByPageLink(pageLink);
    }

//    public Optional<Tip> get(long id) {
//        return tipDao.get(id);
//    }
//
//    public void deleteTip(long id) {
//        tipDao.deleteTip(id);
//    }
//
    public List<Tip> getAll() {
        return tipJpaDao.findAll();
//        return tipDao.getAll();
    }
//
//    public Comment get(Tip tip) {
//        return tipDao.get(tip);
//    }
//
//    public String getCommentString(Comment comment) {
//        return tipDao.getCommentString(comment);
//    }

}
