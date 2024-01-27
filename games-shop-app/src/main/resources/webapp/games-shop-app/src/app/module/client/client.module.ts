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
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ViewOrdersComponent } from './components/view-orders/view-orders.component';
import { MatSortModule } from '@angular/material/sort';
import { PayOrderComponent } from './components/pay-order/pay-order.component';

@NgModule({
  declarations: [
    ViewShoppingCartComponent,
    RegisterComponent,
    ViewOrdersComponent,
    PayOrderComponent,
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatSnackBarModule,
    MatCardModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSnackBarModule,
    MatSortModule,
    MatDialogModule,
  ],
})
export class ClientModule {}
