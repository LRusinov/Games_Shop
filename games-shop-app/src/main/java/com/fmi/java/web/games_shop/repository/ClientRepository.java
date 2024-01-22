package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {}
