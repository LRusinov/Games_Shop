import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewShoppingCartComponent } from './components/view-shopping-cart/view-shopping-cart.component';

const routes: Routes = [{ path: '', component: ViewShoppingCartComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClientRoutingModule {}
