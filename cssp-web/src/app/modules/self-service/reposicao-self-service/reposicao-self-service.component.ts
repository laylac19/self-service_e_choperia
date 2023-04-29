import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NotificacaoCozinhaModel} from "../../../model/notificacao-cozinha.model";
import {SelfServiceService} from "../../../shared/service/self-service.service";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {CozinhaService} from "../../../shared/service/cozinha.service";
import {MensagensSelfServiceUtil} from "../../produto/util/messages/mensagens-self-service.util";

@Component({
  selector: 'app-reposicao-self-service',
  templateUrl: './reposicao-self-service.component.html',
  styleUrls: ['./reposicao-self-service.component.scss']
})
export class ReposicaoSelfServiceComponent implements OnInit {

  formGroup: FormGroup;
  list: boolean = false;

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  constructor(private builder: FormBuilder,
              private kitchenService: CozinhaService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      descricaoPrato: [null, [Validators.required]],
    });
  }

  sendRequestDish(): void {
    const notify = this.formGroup.getRawValue();
    this.kitchenService.needToReplacePlate(notify.descricaoPrato).subscribe(() => {
      this.message.showSuccess(MensagensSelfServiceUtil.SUCCESS_REQUESTING_REPLACEMENT);
    });
  }
}
