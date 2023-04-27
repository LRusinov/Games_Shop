import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GameRoutingModule } from './game-routing.module';
import { ViewGamesComponent } from './components/view-games/view-games.component';
import { CreateGameComponent } from './components/create-game/create-game.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

@NgModule({
  declarations: [
    ViewGamesComponent,
    CreateGameComponent
  ],
  imports: [
    CommonModule,
    GameRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatButtonModule
  ]
})
export class GameModule { }
