import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginModule } from './module/login/login.module';
import { LoginComponent } from './module/login/components/login/login.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'login',
    loadChildren: () =>
      import('./module/login/login.module').then((m) => m.LoginModule),
  },
  {
    path: 'game',
    loadChildren: () =>
      import('./module/game/game.module').then((m) => m.GameModule),
  },
  {
    path: 'add-publisher',
    loadChildren: () =>
      import('./module/publisher/publisher.module').then(
        (m) => m.PublisherModule
      ),
  },
  {
    path: 'add-genre',
    loadChildren: () =>
      import('./module/genre/genre.module').then((m) => m.GenreModule),
  },
  {
    path: 'add-platform',
    loadChildren: () =>
      import('./module/platform/platform.module').then((m) => m.PlatformModule),
  },
  {
    path: 'client',
    loadChildren: () =>
      import('./module/client/client.module').then((m) => m.ClientModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
