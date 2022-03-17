package com.example.demo.dao.implementation;

import com.example.demo.dao.DonationDao;
import com.example.demo.model.tip.Comment;
import com.example.demo.model.tip.Tip;
import com.example.demo.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DonationDaoMem implements DonationDao {

    private static List<Tip> tips;

    @Override
    public Optional<Tip> get(long id) {
        return tips.stream().filter(tip -> tip.isSameId(id)).findFirst();
    }

    @Override
    public void add(Tip tip) {
        tips.add(tip);
    }

    @Override
    public void deleteTip(long id) {
        Tip tipStream = tips.stream().filter(tip -> tip.isSameId(id)).collect(Collectors.toList()).get(0);
        tips.remove(tipStream);
    }

    @Override
    public List<Tip> getAll() {
        return tips;
    }

    @Override
    public Comment get(Tip tip) {
        return tip.getComment();
    }

    @Override
    public String getCommentString(Comment comment) {
        return comment.getComment();
    }


}
