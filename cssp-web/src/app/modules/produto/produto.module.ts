import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoRoutingModule } from './produto-routing.module';
import { ChopeProdComponent } from './chope-prod/chope-prod.component';
import { SelfServiceProdComponent } from './self-service-prod/self-service-prod.component';
import { InsumoProdComponent } from './insumo-prod/insumo-prod.component';
import { ProdutoListComponent } from './produto-list/produto-list.component';
import {SharedModule} from "../../shared/shared.module";
import { InsumoListComponent } from './insumo-list/insumo-list.component';
import { SelfServiceListComponent } from './self-service-list/self-service-list.component';
import { ChopeListComponent } from './chope-list/chope-list.component';


@NgModule({
  declarations: [
    ChopeProdComponent,
    SelfServiceProdComponent,
    InsumoProdComponent,
    ProdutoListComponent,
    InsumoListComponent,
    SelfServiceListComponent,
    ChopeListComponent
  ],
    imports: [
        CommonModule,
        ProdutoRoutingModule,
        SharedModule
    ]
})
export class ProdutoModule { }
