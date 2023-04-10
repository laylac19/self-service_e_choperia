import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SelfServiceRoutingModule } from './self-service-routing.module';
import { SelfServiceComponent } from './self-service/self-service.component';
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [
        SelfServiceComponent
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
