package com.fmi.java.web.games_shop.dto;

import com.fmi.java.web.games_shop.model.ClientRole;

public record ClientDTO(String username, String password, ClientRole role) {
}
