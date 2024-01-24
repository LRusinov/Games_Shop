package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.*;
import com.fmi.java.web.games_shop.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games-shop/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/shopping-cart/{username}")
    public ResponseEntity<List<ShoppingCartItemDTO>> getShoppingCartItems(@PathVariable("username") final String username) {
        return new ResponseEntity<>(clientService.getAllShoppingCartItems(username), HttpStatus.OK);
    }

    @GetMapping("/order-history/{username}")
    public ResponseEntity<List<OrderDTO>> getOrderHistory(@PathVariable("username") final String username) {
        return new ResponseEntity<>(clientService.getOrderHistory(username), HttpStatus.OK);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody final PurchaseOrderDTO purchaseOrderDTO) {
        return new ResponseEntity<>(clientService.addOrder(purchaseOrderDTO), HttpStatus.OK);
    }

    @PutMapping("/shopping-cart")
    public ResponseEntity<List<ShoppingCartItemDTO>> addToShoppingCart(@RequestBody final ShoppingCartItemRequestDTO shoppingCartItemDTO) {
        return new ResponseEntity<>(clientService.addToShoppingCart(shoppingCartItemDTO), HttpStatus.OK);
    }

    @PostMapping("/shopping-cart")
    public ResponseEntity<List<ShoppingCartItemDTO>> removeFromShoppingCart(@RequestBody final ShoppingCartItemRequestDTO shoppingCartItemDTO) {
        return new ResponseEntity<>(clientService.removeFromShoppingCart(shoppingCartItemDTO), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody ClientDTO clientDTO){
        return new ResponseEntity<>(clientService.registerClient(clientDTO), HttpStatus.OK);
    }
}
