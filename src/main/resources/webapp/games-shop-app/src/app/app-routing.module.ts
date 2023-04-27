import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewGamesComponent } from './module/game/components/view-games/view-games.component';

const routes: Routes = [
  {
    path:'game',
    loadChildren:()=>
    import('./module/game/game.module').then(m=>m.GameModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash : true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
