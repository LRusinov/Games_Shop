package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.*;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.*;
import com.fmi.java.web.games_shop.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ShoppingCartItemService shoppingCartItemService;
    private final OrderService orderService;
    private final GameService gameService;
    private static final String EXCEPTION_MESSAGE = "Client with username \"%s\" does not exist.";

    public Set<ShoppingCartItemDTO> getAllShoppingCartItems(final String username) {
        Client client = clientRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, username)));
        return client.getShoppingCartItems().stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    public List<OrderDTO> getOrderHistory(final String username) {
        Client client = clientRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, username)));
        return client.getOrders().stream().map(this::convertToDto).toList();
    }

    public OrderDTO addOrder(PurchaseOrderDTO purchaseOrderDTO) {
        Client client = clientRepository.findById(purchaseOrderDTO.username())
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, purchaseOrderDTO.username())));
        Order newOrder = orderService.createOrder(new Order(Instant.now(), null, client, purchaseOrderDTO.orderItems().stream().map(this::convertToEntity).toList()));
        client.getOrders().add(newOrder);
        clientRepository.save(client);
        return convertToDto(newOrder);
    }

    public Set<ShoppingCartItemDTO> addToShoppingCart(final ShoppingCartItemRequestDTO shoppingCartItemDTO) {
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
            return shoppingCartItems.stream().map(this::convertToDto).collect(Collectors.toSet());
        }).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, shoppingCartItemDTO.clientUsername())));
    }

    public Set<ShoppingCartItemDTO> removeFromShoppingCart(final ShoppingCartItemRequestDTO shoppingCartItemDTO) {
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
                        } else {
                            shoppingCartItemService.removedShoppingCartItem(new ShoppingCartItemId(game.getName(), client.getUsername()));
                            continue;
                        }
                    }
                    newShoppingCartItems.add(item);
                }
                client.setShoppingCartItems(newShoppingCartItems);
                clientRepository.save(client);
                return newShoppingCartItems.stream().map(this::convertToDto).collect(Collectors.toSet());
            }
            throw new EntityNotFoundException(String.format("Client does not have shopping cart item \"%s\" to be deleted.", game.getName()));
        }).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, shoppingCartItemDTO.clientUsername())));
    }

    private OrderItem convertToEntity(final OrderItemDTO orderItemDTO) {
        return new OrderItem(gameService.dtoToEntity(gameService.getGameById(orderItemDTO.gameName())), orderItemDTO.quantity());
    }

    private OrderDTO convertToDto(final Order order) {
        return new OrderDTO(order.getId(), order.getDateOfCreation(), order.getEstimatedDate(), order.getDateOfArrival(), order.getTotalPrice());
    }

    private ShoppingCartItemDTO convertToDto(ShoppingCartItem shoppingCartItem){
        return new ShoppingCartItemDTO(gameService.entityToDto(shoppingCartItem.getGame()), shoppingCartItem.getQuantity());
    }
}
