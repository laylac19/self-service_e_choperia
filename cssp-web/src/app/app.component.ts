import {Component} from '@angular/core';
import {SidemenuModel} from "./shared/models/sidemenu.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'cssp-web';
  public configuracaoMenuLateral: SidemenuModel = new SidemenuModel();

}
