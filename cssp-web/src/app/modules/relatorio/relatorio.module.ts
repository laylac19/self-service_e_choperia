import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RelatorioRoutingModule} from './relatorio-routing.module';
import {RelatorioComponent} from './relatorio/relatorio.component';
import {SharedModule} from "../../shared/shared.module";
import {OverlayPanelModule} from "primeng/overlaypanel";
import {ModalDatasRelatorioComponent} from './modal-datas-relatorio/modal-datas-relatorio.component';


@NgModule({
  declarations: [
    RelatorioComponent,
    ModalDatasRelatorioComponent
  ],
  imports: [
    CommonModule,
    RelatorioRoutingModule,
    SharedModule,
    OverlayPanelModule
  ]
})
export class RelatorioModule {
}
