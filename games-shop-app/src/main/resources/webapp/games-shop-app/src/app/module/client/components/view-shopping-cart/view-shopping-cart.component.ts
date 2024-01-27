import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { ShoppingCartItem } from 'src/app/model/ShoppingCartItem';
import { MatTableDataSource } from '@angular/material/table';
import { OrderItem } from 'src/app/model/OrderItem';
import { User } from 'src/app/model/Client';
import { MatDialog } from '@angular/material/dialog';
import { PayOrderComponent } from '../pay-order/pay-order.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-view-shopping-cart',
  templateUrl: './view-shopping-cart.component.html',
  styleUrls: ['./view-shopping-cart.component.css'],
})
export class ViewShoppingCartComponent implements OnInit {
  user = new User();
  cartItems: ShoppingCartItem[] = [];
  columnsToDisplay = [
    'name',
    'picture',
    'price',
    'quantity',
    'multiplied-price',
  ];
  public dataSource = new MatTableDataSource<ShoppingCartItem>();

  constructor(
    private readonly snackBar: MatSnackBar,
    private readonly clientService: ClientService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.user =
      sessionStorage.getItem('userdetails') != undefined
        ? JSON.parse(sessionStorage.getItem('userdetails')!)
        : undefined;
    if (this.user != undefined) {
      this.clientService
        .getShoppingCartItems(this.user.username)
        .subscribe((response) => {
          this.cartItems = response;
          this.dataSource.data = this.cartItems;
        });
    }
  }

  onAddClick(gameName: string): void {
    this.clientService
      .addToShoppingCart(gameName, this.user.username)
      .subscribe((response) => {
        this.cartItems = response;
        this.dataSource.data = this.cartItems;
      });
  }

  onRemoveClick(gameName: string): void {
    this.clientService
      .removeFromShoppingCart(gameName, this.user.username)
      .subscribe((response) => {
        this.cartItems = response;
        this.dataSource.data = this.cartItems;
      });
  }

  onSubmitOrderClick(): void {
    this.dialog
      .open(PayOrderComponent, {
        width: '550px',
        height: '450px',
        data: this.getTotalCost(),
      })
      .afterClosed()
      .subscribe((res) => {
        if (res) {
          const orderItems: OrderItem[] = this.cartItems.map((cartItem) => {
            return {
              gameName: cartItem.game.name,
              price: cartItem.game.price,
              quantity: cartItem.quantity,
            };
          });
          this.clientService
            .createOrder(this.user.username, orderItems)
            .subscribe({
              next: (response) => {
                this.snackBar
                  .open('You have successfully submited your order!', 'Okay')
                  .afterDismissed()
                  .subscribe(() => {
                    window.location.reload();
                  });
              },
              error: (err: HttpErrorResponse) => {
                this.snackBar.open('An error occured.', 'Okay');
              },
            });
        }
      });
  }

  getTotalCost(): number {
    return this.cartItems
      .map((item) => item.game.price * item.quantity)
      .reduce((acc, value) => acc + value, 0);
  }
}
