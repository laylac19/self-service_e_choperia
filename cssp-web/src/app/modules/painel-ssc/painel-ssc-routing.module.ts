import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PainelClienteComponent} from "./painel-cliente.component";

const routes: Routes = [
  {path: '', component: PainelClienteComponent}
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PainelSscRoutingModule { }
