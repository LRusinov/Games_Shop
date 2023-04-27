package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,String> {
}
