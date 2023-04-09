import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SelfServiceRoutingModule } from './self-service-routing.module';
import { SelfServiceComponent } from './self-service/self-service.component';


@NgModule({
    declarations: [
        SelfServiceComponent
    ],
    exports: [
        SelfServiceComponent
    ],
    imports: [
        CommonModule,
        SelfServiceRoutingModule
    ]
})
export class SelfServiceModule { }
