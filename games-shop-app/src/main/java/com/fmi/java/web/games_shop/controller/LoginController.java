package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.ClientDTO;
import com.fmi.java.web.games_shop.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/games-shop")
public class LoginController {

    private final ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody ClientDTO clientDTO){
        return new ResponseEntity<>(clientService.registerClient(clientDTO), HttpStatus.OK);
    }

    @RequestMapping("/login")
    public ClientDTO getUserDetailsAfterLogin(Authentication authentication) {
        return clientService.findClientByUsername(authentication.getName()).orElse(null);
    }

}
