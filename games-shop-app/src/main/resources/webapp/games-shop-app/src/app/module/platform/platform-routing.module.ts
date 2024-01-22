import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePlatformComponent } from './components/create-platform/create-platform.component';

const routes: Routes = [{ path: '', component: CreatePlatformComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PlatformRoutingModule {}
