import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ImprimirRoutingModule } from './imprimir-routing.module';
import {ImprimirCodbarrasComponent} from "./imprimirCodBarras/imprimir-codbarras.component";
import {ComponentsModule} from "../../shared/components/components.module";
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../../shared/shared.module";
import {ToolbarModule} from "primeng/toolbar";


@NgModule({
  declarations: [
    ImprimirCodbarrasComponent
  ],
    imports: [
        SharedModule,
        CommonModule,
        ImprimirRoutingModule,
        ComponentsModule,
        ReactiveFormsModule,
        ToolbarModule
    ]
})
export class ImprimirModule { }
