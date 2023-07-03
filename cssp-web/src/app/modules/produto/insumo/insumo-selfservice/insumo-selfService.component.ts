import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProdutoModel} from "../../../../model/produto.model";
import {MensagensConfirmacao} from "../../../../shared/util/msgConfirmacaoDialog.util";
import {ProdutoService} from "../../../../shared/service/produto.service";
import {MensagensSelfServiceUtil} from "../../util/messages/mensagens-self-service.util";

@Component({
  selector: 'app-insumo-selfService',
  templateUrl: './insumo-selfService.component.html',
  styleUrls: ['./insumo-selfService.component.scss']
})
export class InsumoSelfServiceComponent implements OnInit {

  @Input() inputModel: ProdutoModel;
  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;
  newInput: ProdutoModel;

  list: boolean = false;

  constructor(private builder: FormBuilder,
              private inputService: ProdutoService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      precoVenda: [null, [Validators.required]],
      descricao: [null, [Validators.required]],
    });
  }

  saveInputForm(): void {
    this.newInput = this.formGroup.getRawValue();
    this.newInput.unidade = 'Kilo';
    this.inputService.save(this.newInput)
      .subscribe({
        next: () => {
          this.showSuccessMsgAccordingToId(this.newInput.id);
          this.closeForm();
          this.list = true;
        },
        error: (error) => {
          this.showErrorMsgAccordingToId(this.newInput.id, error.message);
        }
      });
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
  }

  private showSuccessMsgAccordingToId(idInput: number): void {
    idInput ? this.message.showSuccess(MensagensSelfServiceUtil.UPDATE_SUCCESSFUL_DISH)
      : this.message.showSuccess(MensagensSelfServiceUtil.SUCCESS_CREATED_DISH);
  }

  private showErrorMsgAccordingToId(idInput: number, errorMsg: string): void {
    idInput ? this.message.showError(MensagensSelfServiceUtil.ERROR_UPDATE, errorMsg)
      : this.message.showError(MensagensSelfServiceUtil.ERROR_CREATED, errorMsg);
  }

}
