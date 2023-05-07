import {Component, Input, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {RelatorioEnviarEmailComponent} from "../relatorio-enviar-email/relatorio-enviar-email.component";

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.scss']
})
export class RelatorioComponent {

  titleDialog: "RELATÓRIO - Envio de E-mail"

  @BlockUI() blockUI: NgBlockUI;
  @Input() display = false;

  @ViewChild(RelatorioEnviarEmailComponent) sendEmailComponent: RelatorioEnviarEmailComponent;

  openRequestToSendEmail() {
    this.sendEmailComponent.formGroup.reset();
    this.display = true;
  }

  sendEmail() {
    this.sendEmailComponent.onSend();
    this.onClose()
  }

  onClose(): void {
    this.sendEmailComponent.formGroup.reset();
    this.display = false;
  }
}
