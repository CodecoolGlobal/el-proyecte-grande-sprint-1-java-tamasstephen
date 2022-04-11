package com.example.demo.dao.implementation;

import com.example.demo.model.tip.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipJpaDao extends JpaRepository<Tip, Long> {

    Tip save(Tip tip);

    Optional<Tip> getTipById(long id);

    Tip deleteById(long id);

    List<Tip> findAll();

    List<Tip> findTipsByPageLink(String pageLink);

}
