import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewGenresComponent } from './components/view-genres/view-genres.component';
import { CreateGenreComponent } from './components/create-genre/create-genre.component';

const routes: Routes = [  { path: '', component: ViewGenresComponent },
{ path: 'create', component: CreateGenreComponent },];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GenreRoutingModule { }
