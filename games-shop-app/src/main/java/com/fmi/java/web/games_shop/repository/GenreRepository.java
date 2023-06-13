package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, String> {
}
