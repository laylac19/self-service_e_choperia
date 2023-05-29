import {Component, EventEmitter, Input} from '@angular/core';
import {SidemenuModel} from "../../shared/models/sidemenu.model";
import {AuthService} from "../../modules/login/auth.service";

@Component({
    selector: 'app-topbar',
    templateUrl: './topbar.component.html',
    styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent {

    @Input() public configuracaoMenuLateral?: SidemenuModel;
    constructor(private authService: AuthService) {
    }

    ngOnInit(): void {
    }

    public alternarVisibilidadeMenuLateral(): void {
        if (this.configuracaoMenuLateral) {
          this.configuracaoMenuLateral.visivel = !this.configuracaoMenuLateral.visivel;
        }
    }

  public fazerLogout(): void{
      location.reload();
  }

  public nomeUsuarioLogado(){
      return this.authService.nomeUsuario();
  }

    public fecharMenuLateral(): void {
        if (this.configuracaoMenuLateral) {
            this.configuracaoMenuLateral.visivel = !this.configuracaoMenuLateral.visivel;
        }
    }
}
