package com.example.demo.dao.implementation;

import com.example.demo.dao.TipDao;
import com.example.demo.model.tip.Comment;
import com.example.demo.model.tip.Tip;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TipDaoMem implements TipDao {

    private static final List<Tip> TIPS = new ArrayList<>();

    @Override
    public Optional<Tip> get(long id) {
        return TIPS.stream().filter(tip -> tip.isSameId(id)).findFirst();
    }

    @Override
    public void add(Tip tip) {
        LocalDate now = LocalDate.now();
        long tipId = TIPS.size() + 1;
        tip.setDate(now);
        tip.setId(tipId);
        TIPS.add(tip);
    }

    @Override
    public void deleteTip(long id) {
        Tip tipStream = TIPS.stream().filter(tip -> tip.isSameId(id)).collect(Collectors.toList()).get(0);
        TIPS.remove(tipStream);
    }

    @Override
    public List<Tip> getAll() {
        return TIPS;
    }


    @Override
    public List<Tip> getCommentsByPageLink(String pageLink) {
        return TIPS.stream()
                .filter(tip -> tip.hasSameLink(pageLink))
                .collect(Collectors.toList());
    }


}
