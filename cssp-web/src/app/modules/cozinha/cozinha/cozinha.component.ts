import {Component, Input, OnInit} from '@angular/core';
import {NotificacaoCozinhaListModel} from "../../../model/list/notificacao-cozinha-list.model";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {CozinhaService} from "../../../shared/service/cozinha.service";
import {finalize} from "rxjs";
import {MensagensUsuarioUtil} from "../../usuario/util/mensagens-usuario.util";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";

@Component({
  selector: 'app-cozinha',
  templateUrl: './cozinha.component.html',
  styleUrls: ['./cozinha.component.scss']
})
export class CozinhaComponent implements OnInit {

  notificationList: NotificacaoCozinhaListModel[] | any = [];

  @BlockUI() blockUI: NgBlockUI;
  @Input() display = false;

  constructor(private notificationService: CozinhaService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.blockUI.start();
    this.notificationService.findAll()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          this.message.showInfo(MensagensUsuarioUtil.ERROS_LIST_ALL, MensagensProntasUtil.ERROR);
        }
      })
  }

  listAllUsers(): void {
    this.blockUI.start();
    this.notificationService.findAll()
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          this.message.showInfo(MensagensUsuarioUtil.ERROS_LIST_ALL, MensagensProntasUtil.ERROR);
        }
      })
  }

  private resultRequestList(result: NotificacaoCozinhaListModel[]): void {
    result ? this.notificationList = result : this.notificationList = [];
  }

  deactivateUser(id: number): void {
    this.notificationService.replacePlate(id).subscribe(() => this.listAllUsers());
  }

  confirmAction(item: any): void {
    this.message.confirmReplenishment(item.id, () => this.deactivateUser(item.id), item.prato);
  }

}
