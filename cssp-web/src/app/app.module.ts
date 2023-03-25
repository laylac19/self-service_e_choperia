import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TopbarComponent} from "./components/topbar/topbar.component";
import {SidemenuComponent} from "./components/sidemenu/sidemenu.component";
import {SharedModule} from "./shared/shared.module";
import {ConfirmationService} from "primeng/api";
import {MensagensConfirmacao} from "./shared/util/msgConfirmacaoDialog.util";

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    SidemenuComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
  ],
  providers: [
    ConfirmationService, MensagensConfirmacao
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
