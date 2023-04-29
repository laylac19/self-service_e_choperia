import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SelfServiceRoutingModule } from './self-service-routing.module';
import { SelfServiceComponent } from './self-service/self-service.component';
import {SharedModule} from "../../shared/shared.module";
import { ReposicaoSelfServiceComponent } from './reposicao-self-service/reposicao-self-service.component';


@NgModule({
    declarations: [
        SelfServiceComponent,
        ReposicaoSelfServiceComponent
    ],
    exports: [
        SelfServiceComponent
    ],
    imports: [
        CommonModule,
        SelfServiceRoutingModule,
        SharedModule
    ]
})
export class SelfServiceModule { }
