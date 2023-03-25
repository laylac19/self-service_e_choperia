import {CommonModule} from '@angular/common';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CustomDialogComponent} from './custom-dialog/custom-dialog.component';
import {PRIMENG_IMPORTS} from "../imports/primeng.imports";

@NgModule({
  declarations: [
    CustomDialogComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    PRIMENG_IMPORTS,
  ],
  exports: [
    CustomDialogComponent,
  ],
  entryComponents: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComponentsModule {
}
