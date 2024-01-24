import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewShoppingCartComponent } from './components/view-shopping-cart/view-shopping-cart.component';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  { path: 'shopping-cart', component: ViewShoppingCartComponent },
  { path: 'register', component: RegisterComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClientRoutingModule {}
