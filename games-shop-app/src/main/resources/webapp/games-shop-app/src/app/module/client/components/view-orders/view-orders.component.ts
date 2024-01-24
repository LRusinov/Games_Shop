import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { User } from 'src/app/model/Client';
import { GameService } from 'src/app/module/game/services/game.service';
import { ClientService } from '../../services/client.service';
import { PurchaseOrder } from 'src/app/model/PurchaseOrder';
import { MatTableDataSource } from '@angular/material/table';
import { Game } from 'src/app/model/Game';

@Component({
  selector: 'app-view-orders',
  templateUrl: './view-orders.component.html',
  styleUrls: ['./view-orders.component.css'],
})
export class ViewOrdersComponent {
  orders: PurchaseOrder[] = [];
  user = new User();
  public dataSource = new MatTableDataSource<PurchaseOrder>();

  constructor(private readonly clientService: ClientService) {}
  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    this.clientService
      .getOrderHistory(this.user.username)
      .subscribe((response) => {
        this.orders = response;
        this.dataSource.data = this.orders;
      });
    console.log(this.orders);
  }
}
