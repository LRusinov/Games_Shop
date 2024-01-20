package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.ShoppingCartItemDTO;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Client;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.model.ShoppingCartItem;
import com.fmi.java.web.games_shop.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ShoppingCartItemService shoppingCartItemService;
    private final GameService gameService;
    private static final String EXCEPTION_MESSAGE = "Client with username \"%s\" does not exist.";

    public ClientService(ClientRepository clientRepository, ShoppingCartItemService shoppingCartItemService, GameService gameService) {
        this.clientRepository = clientRepository;
        this.shoppingCartItemService = shoppingCartItemService;
        this.gameService = gameService;
    }

    public Set<ShoppingCartItem> getAllShoppingCartItems(final String username){
        Client client = clientRepository.findById(username)
                        .orElseThrow(()->new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, username)));
        return client.getShoppingCartItems();
    }

    public Client addToShoppingCart(final ShoppingCartItemDTO shoppingCartItemDTO){
       return clientRepository.findById(shoppingCartItemDTO.clientUsername()).map(client->{
            Set<ShoppingCartItem> shoppingCartItems = client.getShoppingCartItems();

            Game game = gameService.dtoToEntity(gameService.getGameById(shoppingCartItemDTO.gameName()));
            ShoppingCartItem shoppingCartItemToAdd = new ShoppingCartItem(game, client, 1);

            if(shoppingCartItems.contains(shoppingCartItemToAdd)){
                client.setShoppingCartItems(shoppingCartItems.stream().map(item->{
                    if(shoppingCartItemDTO.gameName().equals(item.getGame().getName())){
                        item.setQuantity(item.getQuantity() + 1);
                    }
                    return item;
                }).collect(Collectors.toSet()));
            }else {
                shoppingCartItems.add(shoppingCartItemService.addShoppingCartItem(shoppingCartItemToAdd));
            }
            return clientRepository.save(client);
        }).orElseThrow(()->new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, shoppingCartItemDTO.clientUsername())));
    }
}
