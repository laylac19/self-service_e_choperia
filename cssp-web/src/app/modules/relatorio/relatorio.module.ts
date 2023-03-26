import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RelatorioRoutingModule } from './relatorio-routing.module';
import { RelatorioComponent } from './relatorio/relatorio.component';


@NgModule({
  declarations: [
    RelatorioComponent
  ],
  imports: [
    CommonModule,
    RelatorioRoutingModule
  ]
})
export class RelatorioModule { }
