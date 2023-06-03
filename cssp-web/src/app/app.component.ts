import {Component} from '@angular/core';
import {SidemenuModel} from "./shared/models/sidemenu.model";
import {AuthService} from "./modules/login/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'cssp-web';

  mostrarMenu: boolean = false;
  role: string | null = localStorage.getItem('roleDescription')
  public configuracaoMenuLateral: SidemenuModel = new SidemenuModel();

  constructor(private authService: AuthService) {

  }

  ngOnInit(){
    this.authService.mostrarMenuEmitter.subscribe(
      mostrar => this.mostrarMenu = mostrar
    );
  }
}
