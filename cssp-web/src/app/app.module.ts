import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TopbarComponent} from "./components/topbar/topbar.component";
import {SidemenuComponent} from "./components/sidemenu/sidemenu.component";
import {SharedModule} from "./shared/shared.module";
import {ConfirmationService, MessageService} from "primeng/api";
import {MensagensConfirmacao} from "./shared/util/msgConfirmacaoDialog.util";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AuthService} from "./modules/login/auth.service";
import {LoginModule} from "./modules/login/login.module";
import {LoginViewComponent} from "./modules/login/login-view/login-view.component";

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    SidemenuComponent,
    LoginViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    BrowserAnimationsModule,
  ],
  providers: [
    MessageService, ConfirmationService, MensagensConfirmacao, AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
