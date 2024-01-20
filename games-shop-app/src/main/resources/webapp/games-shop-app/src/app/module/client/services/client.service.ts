import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
}
