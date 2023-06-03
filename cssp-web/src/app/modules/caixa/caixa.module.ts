import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CaixaRoutingModule } from './caixa-routing.module';
import { CaixaComponent } from './caixa/caixa-component';
import {ComponentsModule} from "../../shared/components/components.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ClienteCaixaComponent} from "./cliente-caixa/cliente-caixa-component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [
        CaixaComponent,
        ClienteCaixaComponent
    ],
    exports: [
        CaixaComponent
    ],
    imports: [
        SharedModule,
        CommonModule,
        CaixaRoutingModule,
        ComponentsModule,
        ReactiveFormsModule
    ]
})
export class CaixaModule { }
