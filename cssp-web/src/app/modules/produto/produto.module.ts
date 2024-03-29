import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoRoutingModule } from './produto-routing.module';
import { ChopeProdComponent } from './chope/chope-prod/chope-prod.component';
import { InsumoProdComponent } from './insumo/insumo-prod/insumo-prod.component';
import { InsumoEntryComponent } from './insumo/insumo-entry/insumo-entry.component';
import { InsumoWithdrawComponent } from './insumo/insumo-withdraw/insumo-withdraw.component';
import { ProdutoListComponent } from './produto-list/produto-list.component';
import {SharedModule} from "../../shared/shared.module";
import { InsumoListComponent } from './insumo/insumo-list/insumo-list.component';
import { ChopeListComponent } from './chope/chope-list/chope-list.component';
import { ChopeEntryComponent } from './chope/chope-entry/chope-entry.component';
import {InsumoSelfServiceComponent} from "./insumo/insumo-selfservice/insumo-selfService.component";


@NgModule({
  declarations: [
    ChopeProdComponent,
    InsumoProdComponent,
    ProdutoListComponent,
    InsumoListComponent,
    ChopeListComponent,
    InsumoEntryComponent,
    InsumoWithdrawComponent,
    InsumoSelfServiceComponent,
    ChopeEntryComponent
  ],
  exports: [
    ProdutoListComponent
  ],

  imports: [
    CommonModule,
    ProdutoRoutingModule,
    SharedModule
  ]
})
export class ProdutoModule { }
