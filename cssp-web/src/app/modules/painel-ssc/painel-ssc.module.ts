import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PainelSscRoutingModule } from './painel-ssc-routing.module';
import { PainelClienteComponent } from './painel-cliente.component';
import {SharedModule} from "../../shared/shared.module";
import {SelfServiceModule} from "../self-service/self-service.module";
import {ChopeModule} from "../chope/chope.module";


@NgModule({
  declarations: [
    PainelClienteComponent
  ],
  exports: [
    PainelClienteComponent
  ],
  imports: [
    CommonModule,
    PainelSscRoutingModule,
    SharedModule,
    SelfServiceModule,
    ChopeModule
  ]
})
export class PainelSscModule { }
