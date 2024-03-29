import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderItem } from 'src/app/model/OrderItem';
import { PurchaseOrder } from 'src/app/model/PurchaseOrder';
import { ShoppingCartItem } from 'src/app/model/ShoppingCartItem';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  constructor(private readonly httpClient: HttpClient) {}

  getShoppingCartItems(username: string): Observable<ShoppingCartItem[]> {
    return this.httpClient.get<ShoppingCartItem[]>(
      `http://localhost:8080/games-shop/client/shopping-cart/${username}`
    );
  }

  addToShoppingCart(
    gameName: string,
    clientUsername: string
  ): Observable<ShoppingCartItem[]> {
    return this.httpClient.put<ShoppingCartItem[]>(
      'http://localhost:8080/games-shop/client/shopping-cart',
      {
        clientUsername,
        gameName,
      }
    );
  }

  removeFromShoppingCart(
    gameName: string,
    clientUsername: string
  ): Observable<ShoppingCartItem[]> {
    return this.httpClient.post<ShoppingCartItem[]>(
      'http://localhost:8080/games-shop/client/shopping-cart',
      {
        clientUsername,
        gameName,
      }
    );
  }

  createOrder(
    clientUsername: string,
    orderItems: OrderItem[]
  ): Observable<PurchaseOrder> {
    return this.httpClient.put<PurchaseOrder>(
      'http://localhost:8080/games-shop/client/order',
      {
        clientUsername,
        orderItems,
      }
    );
  }
}
