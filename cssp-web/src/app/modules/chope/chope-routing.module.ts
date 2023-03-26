import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ChopeViewComponent} from "./chope-view/chope-view.component";

const routes: Routes = [
  {path: '', component: ChopeViewComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChopeRoutingModule {
}
