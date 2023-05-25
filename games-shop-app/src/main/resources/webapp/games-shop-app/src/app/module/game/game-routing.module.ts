import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewGamesComponent } from './components/view-games/view-games.component';
import { CreateGameComponent } from './components/create-game/create-game.component';

const routes: Routes = [
  { path: '', component: ViewGamesComponent },
  { path: 'create', component: CreateGameComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GameRoutingModule {}
