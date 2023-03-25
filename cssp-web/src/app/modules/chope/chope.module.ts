import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChopeRoutingModule } from './chope-routing.module';
import { ChopeViewComponent } from './chope-view/chope-view.component';


@NgModule({
  declarations: [
    ChopeViewComponent
  ],
  imports: [
    CommonModule,
    ChopeRoutingModule
  ]
})
export class ChopeModule { }
