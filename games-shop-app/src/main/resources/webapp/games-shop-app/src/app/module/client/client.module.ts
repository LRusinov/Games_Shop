import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ViewShoppingCartComponent } from './components/view-shopping-cart/view-shopping-cart.component';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { ClientRoutingModule } from './client-routing.module';

@NgModule({
  declarations: [ViewShoppingCartComponent],
  imports: [
    CommonModule,
    ClientRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    MatButtonModule,
    MatCheckboxModule,
    MatSnackBarModule,
    MatDialogModule,
  ],
})
export class ClientModule {}
