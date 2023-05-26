import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePublisherComponent } from './components/create-publisher/create-publisher.component';
import { ViewPublisherComponent } from './components/view-publisher/view-publisher.component';

const routes: Routes = [{ path: '', component: CreatePublisherComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PublisherRoutingModule {}
