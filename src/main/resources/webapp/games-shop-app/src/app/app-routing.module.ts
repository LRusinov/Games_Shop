import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'game',
    loadChildren: () =>
      import('./module/game/game.module').then((m) => m.GameModule),
  },
  {
    path: 'publishers',
    loadChildren: () =>
      import('./module/publisher/publisher.module').then((m) => m.PublisherModule),
  },
  {
    path: 'genres',
    loadChildren: () =>
      import('./module/genre/genre.module').then((m) => m.GenreModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
