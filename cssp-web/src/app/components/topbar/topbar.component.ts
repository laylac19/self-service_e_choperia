import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {SidemenuModel} from "../../shared/models/sidemenu.model";
import {MensagensConfirmacao} from "../../shared/util/msgConfirmacaoDialog.util";

@Component({
    selector: 'app-topbar',
    templateUrl: './topbar.component.html',
    styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent {

    @Input() public configuracaoMenuLateral?: SidemenuModel;

    constructor(
        private confirmMessage: MensagensConfirmacao,
        private router: Router
    ) {
    }

    ngOnInit(): void {
    }

    public alternarVisibilidadeMenuLateral(): void {
        if (this.configuracaoMenuLateral) {
            this.configuracaoMenuLateral.visivel = !this.configuracaoMenuLateral.visivel;
        }
    }

    public fecharMenuLateral(): void {
        if (this.configuracaoMenuLateral) {
            this.configuracaoMenuLateral.visivel = !this.configuracaoMenuLateral.visivel;
        }
    }
}
