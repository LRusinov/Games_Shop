package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.*;
import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.*;
import com.fmi.java.web.games_shop.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ShoppingCartItemService shoppingCartItemService;
    private final OrderService orderService;
    private final GameService gameService;
    private final PasswordEncoder passwordEncoder;
    private static final String EXCEPTION_MESSAGE = "Client with clientUsername \"%s\" does not exist.";

    public Optional<ClientDTO> findClientByUsername(String username){
        return  entityToDto(clientRepository.findById(username));
    }

    private static Optional<ClientDTO> entityToDto(Optional<Client> client){
        if(client.isPresent()){
            Client c = client.get();
            return Optional.of( new ClientDTO(c.getUsername(), c.getPassword(), c.getRole()));
        }
       return Optional.empty();
    }

    public boolean registerClient(ClientDTO clientDTO){
        if(clientRepository.existsById(clientDTO.username())){
            throw new EntityExistsException(String.format("Client with username \"%s\" already exists.", clientDTO.username()));
        }
        clientRepository.save(new Client(clientDTO.username(), passwordEncoder.encode(clientDTO.password()), ClientRole.CUSTOMER));
        return true;
    }

    public List<ShoppingCartItemDTO> getAllShoppingCartItems(final String username) {
        Client client = clientRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, username)));

        return client.getShoppingCartItems().stream().map(this::convertToDto).toList();
    }

    public List<OrderDTO> getOrderHistory(final String username) {
        Client client = clientRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, username)));
        return client.getOrders().stream().map(this::convertToDto).toList();
    }

    public OrderDTO addOrder(PurchaseOrderDTO purchaseOrderDTO) {
        Client client = clientRepository.findById(purchaseOrderDTO.clientUsername())
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, purchaseOrderDTO.clientUsername())));
        Order newOrder = orderService.createOrder(purchaseOrderDTO.clientUsername(), new Order(Instant.now(), null, client, purchaseOrderDTO.orderItems().stream().map(this::convertToEntity).toList()));
        client.getOrders().add(newOrder);
        clientRepository.save(client);
        return convertToDto(newOrder);
    }

    public List<ShoppingCartItemDTO> addToShoppingCart(final ShoppingCartItemRequestDTO shoppingCartItemDTO) {
        return clientRepository.findById(shoppingCartItemDTO.clientUsername()).map(client -> {
            List<ShoppingCartItem> shoppingCartItems = client.getShoppingCartItems();

            Game game = gameService.dtoToEntity(gameService.getGameById(shoppingCartItemDTO.gameName()));
            ShoppingCartItem shoppingCartItemToAdd = new ShoppingCartItem(game, client, 1);

            int indexOfItem= shoppingCartItems.indexOf(shoppingCartItemToAdd);
            if (indexOfItem != -1) {
                shoppingCartItems.get(indexOfItem).setQuantity(shoppingCartItems.get(indexOfItem).getQuantity() + 1);
            } else {
                shoppingCartItems.add(shoppingCartItemService.addShoppingCartItem(shoppingCartItemToAdd));
            }
            clientRepository.save(client);
            return shoppingCartItems.stream().map(this::convertToDto).toList();
        }).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, shoppingCartItemDTO.clientUsername())));
    }

    public List<ShoppingCartItemDTO> removeFromShoppingCart(final ShoppingCartItemRequestDTO shoppingCartItemDTO) {
        return clientRepository.findById(shoppingCartItemDTO.clientUsername()).map(client -> {
            List<ShoppingCartItem> shoppingCartItems = client.getShoppingCartItems();
            Game game = gameService.dtoToEntity(gameService.getGameById(shoppingCartItemDTO.gameName()));
            ShoppingCartItem shoppingCartItemToBeRemove = new ShoppingCartItem(game, client, 1);

            int indexOfItem= shoppingCartItems.indexOf(shoppingCartItemToBeRemove);
            if (indexOfItem != -1) {
                List<ShoppingCartItem> newShoppingCartItems = new ArrayList<>();
                for (ShoppingCartItem item : shoppingCartItems) {
                    if (shoppingCartItemDTO.gameName().equals(item.getGame().getName())) {
                        if (item.getQuantity() != 1) {
                            item.setQuantity(item.getQuantity() - 1);
                            newShoppingCartItems.add(item);
                        } else {
                            shoppingCartItemService.removedShoppingCartItem(new ShoppingCartItemId(game.getName(), client.getUsername()));
                        }
                        continue;
                    }
                    newShoppingCartItems.add(item);
                }
                client.setShoppingCartItems(newShoppingCartItems);
                clientRepository.save(client);
                return newShoppingCartItems.stream().map(this::convertToDto).toList();
            }
            throw new EntityNotFoundException(String.format("Client does not have shopping cart item \"%s\" to be deleted.", game.getName()));
        }).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, shoppingCartItemDTO.clientUsername())));
    }

    private OrderItem convertToEntity(final OrderItemDTO orderItemDTO) {
        GameDto gameDto = gameService.getGameById(orderItemDTO.gameName());
        return new OrderItem(gameDto.name(),gameDto.price(), orderItemDTO.quantity());
    }

    private OrderDTO convertToDto(final Order order) {
        return new OrderDTO(order.getId(), order.getDateOfCreation(), order.getEstimatedDate(), order.getDateOfArrival(), order.getTotalPrice(), order.getOrderItems().stream().map(ClientService::convertToDto).toList());
    }

    private ShoppingCartItemDTO convertToDto(ShoppingCartItem shoppingCartItem){
        return new ShoppingCartItemDTO(gameService.entityToDto(shoppingCartItem.getGame()), shoppingCartItem.getQuantity());
    }

    private static OrderItemDTO convertToDto(OrderItem orderItem){
        return new OrderItemDTO(orderItem.getGameName(), orderItem.getQuantity());
    }
}
