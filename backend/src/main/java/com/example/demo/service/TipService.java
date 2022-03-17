package com.example.demo.service;

import com.example.demo.dao.DonationDao;
import com.example.demo.model.tip.Comment;
import com.example.demo.model.tip.Tip;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TipService {

    DonationDao donationDao;

    public Optional<Tip> get(long id) {
        return donationDao.get(id);
    }


    public void add(Tip tip) {
        donationDao.add(tip);
    }

    public void deleteTip(long id) {
        donationDao.deleteTip(id);
    }

    public List<Tip> getAll() {
        return donationDao.getAll();
    }

    public Comment get(Tip tip) {
        return donationDao.get(tip);
    }

    public String getCommentString(Comment comment) {
        return donationDao.getCommentString(comment);
    }

}
