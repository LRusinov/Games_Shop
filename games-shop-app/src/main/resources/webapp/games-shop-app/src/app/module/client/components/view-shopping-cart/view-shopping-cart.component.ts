import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { ShoppingCartItem } from 'src/app/model/ShoppingCartItem';

@Component({
  selector: 'app-view-shopping-cart',
  templateUrl: './view-shopping-cart.component.html',
  styleUrls: ['./view-shopping-cart.component.css'],
})
export class ViewShoppingCartComponent {
  cartItems: ShoppingCartItem[] = [];

  constructor(private readonly clientService: ClientService) {}

  ngOnInit(): void {
    this.clientService.getShoppingCartItems('user').subscribe((response) => {
      this.cartItems = response;
    });
  }
}
