import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioFormComponent } from './usuario-form/usuario-form.component';
import { UsuarioListComponent } from './usuario-list/usuario-list.component';
import {SharedModule} from "../../shared/shared.module";


@NgModule({
  declarations: [
    UsuarioFormComponent,
    UsuarioListComponent
  ],
    imports: [
        CommonModule,
        UsuarioRoutingModule,
        SharedModule
    ]
})
export class UsuarioModule { }
