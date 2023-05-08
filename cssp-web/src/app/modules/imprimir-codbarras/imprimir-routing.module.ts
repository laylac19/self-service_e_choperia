import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ImprimirCodbarrasComponent} from "./imprimirCodBarras/imprimir-codbarras.component";

const routes: Routes = [
  {path: '', component: ImprimirCodbarrasComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImprimirRoutingModule {
}
