package com.example.demo.dao;

import com.example.demo.model.tip.Comment;
import com.example.demo.model.tip.Tip;

import java.util.List;
import java.util.Optional;

public interface TipDao {

    Optional<Tip> get(long id);

    void add(Tip tip);

    void deleteTip(long id);

    List<Tip> getAll();

    List<Tip> getCommentsByPageLink(String pageLink);

}
