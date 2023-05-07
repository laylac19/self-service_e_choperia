import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ClienteCompraService} from "../../../shared/service/cliente-compra.service";
import {RelatorioEnviarEmailModel} from "../../../model/relatorio-enviar-email.model";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {FuncoesUtil} from "../../../shared/util/funcoes.util";

@Component({
  selector: 'app-relatorio-enviar-email',
  templateUrl: './relatorio-enviar-email.component.html',
  styleUrls: ['./relatorio-enviar-email.component.scss']
})
export class RelatorioEnviarEmailComponent implements OnInit {
  formGroup: FormGroup;
  dataInicial: Date = new Date();
  dataFinal: Date = new Date();

  report: RelatorioEnviarEmailModel;

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  constructor(private builder: FormBuilder,
              private clientPurchaseService: ClienteCompraService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
  }


  newForm(): void {
    this.formGroup = this.builder.group({
      dataInicial: [null, [Validators.required]],
      dataFinal: [null, [Validators.required]],
      mensagem: [null, [Validators.required]]
    });
  }

  onSend() {
    this.report = this.formGroup.getRawValue();
    this.clientPurchaseService.sendEmail(this.report)
      .subscribe({
        next: () => {
          this.message.showSuccess('E-mail(s) enviado(s) com sucesso!');
          this.closeForm();
        },
        error:(error) => {
          this.message.showError(MensagensProntasUtil.ERROR, MensagensProntasUtil.SUB_MESSAGE_ERROR)
        }
      });
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
  }

}
