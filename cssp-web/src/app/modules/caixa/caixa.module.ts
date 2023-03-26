import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CaixaRoutingModule } from './caixa-routing.module';
import { CaixaComponent } from './caixa/caixa.component';


@NgModule({
  declarations: [
    CaixaComponent
  ],
  imports: [
    CommonModule,
    CaixaRoutingModule
  ]
})
export class CaixaModule { }
