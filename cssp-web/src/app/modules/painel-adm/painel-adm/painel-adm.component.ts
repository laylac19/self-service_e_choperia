import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MenuEnum} from "../../../shared/util/enum/menu-enum";

@Component({
  selector: 'app-painel-adm',
  templateUrl: './painel-adm.component.html',
  styleUrls: ['./painel-adm.component.scss']
})
export class PainelAdmComponent implements OnInit {

  public cardComponent: string;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public mudarRota(idTipoAtendimento: number): void {
    this.cardComponent = MenuEnum.setClasse(idTipoAtendimento).titulo;
    this.router.navigate(['/' + this.cardComponent]);
  }
}
