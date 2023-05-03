import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChopeRoutingModule } from './chope-routing.module';
import { ChopeViewComponent } from './chope-view/chope-view.component';
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [
        ChopeViewComponent
    ],
    exports: [
        ChopeViewComponent
    ],
    imports: [
        CommonModule,
        ChopeRoutingModule,
        SharedModule
    ]
})
export class ChopeModule { }
