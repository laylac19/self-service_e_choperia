import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ClienteRoutingModule} from './cliente-routing.module';
import {ClienteFormComponent} from './cliente-form/cliente-form.component';
import {ClienteListComponent} from './cliente-list/cliente-list.component';
import {SharedModule} from "../../shared/shared.module";
import {ClienteEntradaComponent} from './cliente-entrada/cliente-entrada.component';


@NgModule({
  declarations: [
    ClienteFormComponent,
    ClienteListComponent,
    ClienteEntradaComponent
  ],
  imports: [
    CommonModule,
    ClienteRoutingModule,
    SharedModule
  ]
})
export class ClienteModule {
}
