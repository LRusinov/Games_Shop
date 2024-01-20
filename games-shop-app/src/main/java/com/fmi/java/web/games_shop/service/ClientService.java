package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.ShoppingCartItemDTO;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Client;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.model.ShoppingCartItem;
import com.fmi.java.web.games_shop.model.ShoppingCartItemId;
import com.fmi.java.web.games_shop.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ShoppingCartItemService shoppingCartItemService;
    private final GameService gameService;
    private static final String EXCEPTION_MESSAGE = "Client with username \"%s\" does not exist.";

    public Set<ShoppingCartItem> getAllShoppingCartItems(final String username) {
        Client client = clientRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, username)));
        return client.getShoppingCartItems();
    }

    public Set<ShoppingCartItem> addToShoppingCart(final ShoppingCartItemDTO shoppingCartItemDTO) {
        return clientRepository.findById(shoppingCartItemDTO.clientUsername()).map(client -> {
            Set<ShoppingCartItem> shoppingCartItems = client.getShoppingCartItems();

            Game game = gameService.dtoToEntity(gameService.getGameById(shoppingCartItemDTO.gameName()));
            ShoppingCartItem shoppingCartItemToAdd = new ShoppingCartItem(game, client, 1);

            if (shoppingCartItems.contains(shoppingCartItemToAdd)) {
                client.setShoppingCartItems(shoppingCartItems.stream().map(item -> {
                    if (shoppingCartItemDTO.gameName().equals(item.getGame().getName())) {
                        item.setQuantity(item.getQuantity() + 1);
                    }
                    return item;
                }).collect(Collectors.toSet()));
            } else {
                shoppingCartItems.add(shoppingCartItemService.addShoppingCartItem(shoppingCartItemToAdd));
            }
            clientRepository.save(client);
            return shoppingCartItems;
        }).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, shoppingCartItemDTO.clientUsername())));
    }

    public Set<ShoppingCartItem> removeFromShoppingCart(final ShoppingCartItemDTO shoppingCartItemDTO) {
        return clientRepository.findById(shoppingCartItemDTO.clientUsername()).map(client -> {
            Set<ShoppingCartItem> shoppingCartItems = client.getShoppingCartItems();
            Game game = gameService.dtoToEntity(gameService.getGameById(shoppingCartItemDTO.gameName()));
            ShoppingCartItem shoppingCartItemToBeRemove = new ShoppingCartItem(game, client, 1);

            if (shoppingCartItems.contains(shoppingCartItemToBeRemove)) {
                Set<ShoppingCartItem> newShoppingCartItems = new HashSet<>();
                for (ShoppingCartItem item : shoppingCartItems) {
                    if (shoppingCartItemDTO.gameName().equals(item.getGame().getName())) {
                        if (item.getQuantity() != 1) {
                            item.setQuantity(item.getQuantity() - 1);
                            newShoppingCartItems.add(item);
                        }else {
                            shoppingCartItemService.removedShoppingCartItem(new ShoppingCartItemId(game.getName(), client.getUsername()));
                            continue;
                        }
                    }
                    newShoppingCartItems.add(item);
                }
                client.setShoppingCartItems(newShoppingCartItems);
                clientRepository.save(client);
                return newShoppingCartItems;
            }
            throw new EntityNotFoundException(String.format("Client does not have shopping cart item '%s' to be deleted.", game.getName()));
        }).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, shoppingCartItemDTO.clientUsername())));
    }
}