import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PlatformRoutingModule } from './platform-routing.module';
import { ViewPlatformsComponent } from './components/view-platforms/view-platforms.component';
import { CreatePlatformComponent } from './components/create-platform/create-platform.component';


@NgModule({
  declarations: [
    ViewPlatformsComponent,
    CreatePlatformComponent
  ],
  imports: [
    CommonModule,
    PlatformRoutingModule
  ]
})
export class PlatformModule { }
