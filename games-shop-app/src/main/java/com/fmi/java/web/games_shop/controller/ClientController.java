package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.ShoppingCartItemDTO;
import com.fmi.java.web.games_shop.model.Client;
import com.fmi.java.web.games_shop.model.ShoppingCartItem;
import com.fmi.java.web.games_shop.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/games-shop/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Set<ShoppingCartItem>> getGameById(@PathVariable("username") final String username) {
        return new ResponseEntity<>(clientService.getAllShoppingCartItems(username), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Client> addToShoppingCart( @RequestBody final ShoppingCartItemDTO shoppingCartItemDTO) {
        return new ResponseEntity<>(clientService.addToShoppingCart(shoppingCartItemDTO), HttpStatus.OK);
    }
}
