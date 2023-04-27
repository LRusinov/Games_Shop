package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findByname(String name);
}
