import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoRoutingModule } from './produto-routing.module';
import { ChopeProdComponent } from './chope-prod/chope-prod.component';
import { SelfServiceProdComponent } from './self-service-prod/self-service-prod.component';
import { InsumoProdComponent } from './insumo-prod/insumo-prod.component';
import { ProdutoListComponent } from './produto-list/produto-list.component';


@NgModule({
  declarations: [
    ChopeProdComponent,
    SelfServiceProdComponent,
    InsumoProdComponent,
    ProdutoListComponent
  ],
  imports: [
    CommonModule,
    ProdutoRoutingModule
  ]
})
export class ProdutoModule { }
