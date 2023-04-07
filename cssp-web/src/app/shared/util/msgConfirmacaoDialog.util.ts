import {Injectable} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {EntidadeUtil} from "./entidade.util";

@Injectable()
export class MensagensConfirmacao {

  constructor(private confirmMessage: ConfirmationService,
              private messageService: MessageService) {
  }

  public confirmarDialog(id: number, alterarSituacao: () => void, entidade: EntidadeUtil): void {
    this.confirmMessage.confirm({
      header: 'Confirmação',
      message: 'Deseja desativar esse(a) ' + entidade.descricao + ' ?',
      acceptLabel: 'Sim',
      rejectLabel: 'Cancelar',
      accept: alterarSituacao
    });
  }


  showSuccess(message: string) {
    this.messageService.add({severity: 'success', summary: 'Success', detail: message});
  }

  showInfo(message: string, summary: string) {
    this.messageService.add({severity: 'info', summary: summary, detail: message});
  }

  showWarn(message: string, summary: string) {
    this.messageService.add({severity: 'warn', summary: summary, detail: message});
  }

  showError(message: string, error: string) {
    this.messageService.add({severity: 'error', summary: error, detail: message});
  }
}
