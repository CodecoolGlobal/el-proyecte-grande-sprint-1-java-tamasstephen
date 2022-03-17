package com.example.demo.service;

import com.example.demo.dao.TipDao;
import com.example.demo.model.tip.Comment;
import com.example.demo.model.tip.Tip;

import java.util.List;
import java.util.Optional;

public class TipService {

    TipDao tipDao;

    public void add(Tip tip) {
        tipDao.add(tip);
    }

    public List<Comment> getCommentsByPageLink(String pageLink){
        return tipDao.getCommentsByPageLink(pageLink);
    }

    public Optional<Tip> get(long id) {
        return tipDao.get(id);
    }

    public void deleteTip(long id) {
        tipDao.deleteTip(id);
    }

    public List<Tip> getAll() {
        return tipDao.getAll();
    }

    public Comment get(Tip tip) {
        return tipDao.get(tip);
    }

    public String getCommentString(Comment comment) {
        return tipDao.getCommentString(comment);
    }

}
