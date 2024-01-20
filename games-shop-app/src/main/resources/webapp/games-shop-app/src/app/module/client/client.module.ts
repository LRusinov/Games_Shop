import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { ViewShoppingCartComponent } from './components/view-shopping-cart/view-shopping-cart.component';


@NgModule({
  declarations: [
    ViewShoppingCartComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule
  ]
})
export class ClientModule { }
