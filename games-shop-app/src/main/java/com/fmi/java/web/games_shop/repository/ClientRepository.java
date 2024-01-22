package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {}
