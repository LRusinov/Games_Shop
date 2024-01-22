package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.OrderDTO;
import com.fmi.java.web.games_shop.dto.PurchaseOrderDTO;
import com.fmi.java.web.games_shop.dto.ShoppingCartItemDTO;
import com.fmi.java.web.games_shop.dto.ShoppingCartItemRequestDTO;
import com.fmi.java.web.games_shop.model.Order;
import com.fmi.java.web.games_shop.model.OrderItem;
import com.fmi.java.web.games_shop.model.ShoppingCartItem;
import com.fmi.java.web.games_shop.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/games-shop/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/shopping-cart/{username}")
    public ResponseEntity<Set<ShoppingCartItemDTO>> getShoppingCartItems(@PathVariable("username") final String username) {
        return new ResponseEntity<>(clientService.getAllShoppingCartItems(username), HttpStatus.OK);
    }

    @GetMapping("/order/{username}")
    public ResponseEntity<List<OrderDTO>> getOrderHistory(@PathVariable("username") final String username) {
        return new ResponseEntity<>(clientService.getOrderHistory(username), HttpStatus.OK);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody final PurchaseOrderDTO purchaseOrderDTO) {
        return new ResponseEntity<>(clientService.addOrder(purchaseOrderDTO), HttpStatus.OK);
    }

    @PutMapping("/shopping-cart")
    public ResponseEntity<Set<ShoppingCartItemDTO>> addToShoppingCart(@RequestBody final ShoppingCartItemRequestDTO shoppingCartItemDTO) {
        return new ResponseEntity<>(clientService.addToShoppingCart(shoppingCartItemDTO), HttpStatus.OK);
    }

    @PostMapping("/shopping-cart")
    public ResponseEntity<Set<ShoppingCartItemDTO>> removeFromShoppingCart(@RequestBody final ShoppingCartItemRequestDTO shoppingCartItemDTO) {
        return new ResponseEntity<>(clientService.removeFromShoppingCart(shoppingCartItemDTO), HttpStatus.OK);
    }
}
