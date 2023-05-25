package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByname(String name);
}
