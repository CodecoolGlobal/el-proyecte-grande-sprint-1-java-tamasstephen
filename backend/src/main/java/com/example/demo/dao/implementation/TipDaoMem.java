package com.example.demo.dao.implementation;

import com.example.demo.dao.TipDao;
import com.example.demo.model.tip.Comment;
import com.example.demo.model.tip.Tip;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TipDaoMem implements TipDao {

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

    @Override
    public List<Comment> getCommentsByPageLink(String pageLink) {
        return tips.stream()
                .filter(tip -> tip.hasSameLink(pageLink))
                .map(Tip::getComment)
                .collect(Collectors.toList());
    }


}
