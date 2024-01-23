import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { ShoppingCartItem } from 'src/app/model/ShoppingCartItem';
import { MatTableDataSource } from '@angular/material/table';
import { OrderItem } from 'src/app/model/OrderItem';
import { User } from 'src/app/model/Client';

@Component({
  selector: 'app-view-shopping-cart',
  templateUrl: './view-shopping-cart.component.html',
  styleUrls: ['./view-shopping-cart.component.css'],
})
export class ViewShoppingCartComponent implements OnInit {
  cartItems: ShoppingCartItem[] = [];
  columnsToDisplay = [
    'name',
    'picture',
    'price',
    'quantity',
    'multiplied-price',
  ];
  public dataSource = new MatTableDataSource<ShoppingCartItem>();

  constructor(private readonly clientService: ClientService) {}

  ngOnInit(): void {
    const user: User =
      sessionStorage.getItem('userdetails') != undefined
        ? JSON.parse(sessionStorage.getItem('userdetails')!)
        : undefined;
    if (user != undefined) {
      this.clientService
        .getShoppingCartItems(user.username)
        .subscribe((response) => {
          this.cartItems = response;
          this.dataSource.data = this.cartItems;
        });
    }
  }

  onAddClick(name: string): void {
    this.clientService.addToShoppingCart(name, 'user').subscribe((response) => {
      this.cartItems = response;
      this.dataSource.data = this.cartItems;
    });
  }

  onRemoveClick(name: string): void {
    this.clientService
      .removeFromShoppingCart(name, 'user')
      .subscribe((response) => {
        this.cartItems = response;
        this.dataSource.data = this.cartItems;
      });
  }

  onSubmitOrderClick(): void {
    const orderItems: OrderItem[] = this.cartItems.map((cartItem) => {
      return {
        gameName: cartItem.game.name,
        quantity: cartItem.quantity,
      };
    });
    this.clientService.createOrder('user', orderItems).subscribe();
    window.location.reload();
  }

  getTotalCost(): number {
    return this.cartItems
      .map((item) => item.game.price * item.quantity)
      .reduce((acc, value) => acc + value, 0);
  }
}
