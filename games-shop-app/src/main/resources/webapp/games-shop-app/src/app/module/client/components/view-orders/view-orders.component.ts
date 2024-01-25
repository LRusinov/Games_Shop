import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { User } from 'src/app/model/Client';
import { GameService } from 'src/app/module/game/services/game.service';
import { ClientService } from '../../services/client.service';
import { PurchaseOrder } from 'src/app/model/PurchaseOrder';
import { MatTableDataSource } from '@angular/material/table';
import { Game } from 'src/app/model/Game';
import { MatSort } from '@angular/material/sort';
import {
  trigger,
  state,
  style,
  transition,
  animate,
} from '@angular/animations';

@Component({
  selector: 'app-view-orders',
  templateUrl: './view-orders.component.html',
  styleUrls: ['./view-orders.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition(
        'expanded <=> collapsed',
        animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')
      ),
    ]),
  ],
})
export class ViewOrdersComponent {
  columnsToDisplay = [
    'id',
    'created',
    'estimated',
    'arrived',
    'price',
    'expand',
  ];
  expandedElement: PurchaseOrder | null = null;
  @ViewChild(MatSort)
  sort: MatSort = new MatSort();
  user = new User();
  orders: PurchaseOrder[] = [];
  public dataSource = new MatTableDataSource<PurchaseOrder>();

  constructor(private readonly clientService: ClientService) {}
  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    this.clientService
      .getOrderHistory(this.user.username)
      .subscribe((response) => {
        this.orders = response;
        this.dataSource.data = this.orders;
        this.dataSource.sort = this.sort;
      });
  }
}
