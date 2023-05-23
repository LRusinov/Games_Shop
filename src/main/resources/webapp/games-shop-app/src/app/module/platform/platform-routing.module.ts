import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewPlatformsComponent } from './components/view-platforms/view-platforms.component';
import { CreatePlatformComponent } from './components/create-platform/create-platform.component';

const routes: Routes = [{ path: '', component: ViewPlatformsComponent },
{ path: 'create', component: CreatePlatformComponent },];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlatformRoutingModule { }
