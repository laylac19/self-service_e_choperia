import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RelatorioRoutingModule } from './relatorio-routing.module';
import { RelatorioComponent } from './relatorio/relatorio.component';
import {SharedModule} from "../../shared/shared.module";
import {OverlayPanelModule} from "primeng/overlaypanel";
import { RelatorioEnviarEmailComponent } from './relatorio-enviar-email/relatorio-enviar-email.component';


@NgModule({
  declarations: [
    RelatorioComponent,
    RelatorioEnviarEmailComponent
  ],
    imports: [
        CommonModule,
        RelatorioRoutingModule,
        SharedModule,
        OverlayPanelModule
    ]
})
export class RelatorioModule { }
