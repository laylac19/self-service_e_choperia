import {Component, Input} from '@angular/core';
import {OpcaoMenuModel} from 'src/app/shared/models/opcao-menu.model';
import {SidemenuModel} from 'src/app/shared/models/sidemenu.model';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.scss']
})
export class SidemenuComponent {
  @Input() public configuracaoMenuLateral?: SidemenuModel;

  @Input() public opcoesControleAverco: OpcaoMenuModel[] = [
    new OpcaoMenuModel('pi pi-home', 'Início', 'Início',
      () => this.router.navigateByUrl('/')),
    new OpcaoMenuModel('bi bi-file-earmark-person', 'Usuários', 'Usuários',
      () => this.router.navigateByUrl('/')),
    new OpcaoMenuModel('bi bi-ticket-detailed', 'Relatórios', 'Relatórios',
      () => this.router.navigateByUrl('/')),
    new OpcaoMenuModel('bi bi-person-video3', 'Produtos', 'Produtos',
      () => this.router.navigateByUrl('/')),
    new OpcaoMenuModel('bi bi-film', 'Clientes', 'Clientes',
      () => this.router.navigateByUrl('/')),
    new OpcaoMenuModel('bi bi-upc-scan', 'Caixa', 'Caixa',
      () => this.router.navigateByUrl('/')),
    new OpcaoMenuModel('bi bi-upc-scan', 'Cozinha', 'Caixa',
      () => this.router.navigateByUrl('/')),
    new OpcaoMenuModel('bi bi-upc-scan', 'Produtos', 'Produtos',
      () => this.router.navigateByUrl('/')),
    new OpcaoMenuModel('bi bi-upc-scan', 'Self-Service e Chope', 'Self-Service e Chope',
      () => this.router.navigateByUrl('/')),
  ];

  constructor(
    private router: Router
  ) {
  }

  public estaVisivel(): boolean {
    if (this.configuracaoMenuLateral) {
      return this.configuracaoMenuLateral.visivel;
    }
    return false;
  }
}
