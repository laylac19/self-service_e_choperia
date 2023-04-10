import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {InsumoModel} from "../../../model/insumo.model";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {InsumoService} from "../../../shared/service/insumo.service";
import {MensagensInsumoUtil} from "../util/messages/mensagens-insumo.util";

@Component({
  selector: 'app-insumo-prod',
  templateUrl: './insumo-prod.component.html',
  styleUrls: ['./insumo-prod.component.scss']
})
export class InsumoProdComponent implements OnInit {

  @Input() inputModel: InsumoModel;
  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;
  newInput: InsumoModel;

  list: boolean = false;

  constructor(private builder: FormBuilder,
              private inputService: InsumoService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      codigoBarras: [null, [Validators.required]],
      qtdeEstoque: [null, [Validators.required]],
      precoCompra: [null, [Validators.required]],
      descricao: [null, [Validators.required]],
      unidade: [null, [Validators.required]],
      pontoEncomenda: [null, [Validators.required]],
      precoVenda: [null, [Validators.required]],
    });
  }

  saveInputForm(): void {
    this.newInput = this.formGroup.getRawValue();
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

  editInput(id: number): void {
    this.inputService.findById(id).subscribe({
        next: (response) => {
          this.formGroup.patchValue(response);
        },
      }
    );
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
  }

  private showSuccessMsgAccordingToId(idInput: number): void {
    idInput ? this.message.showSuccess(MensagensInsumoUtil.UPDATE_SUCCESSFUL_INPUT)
      : this.message.showSuccess(MensagensInsumoUtil.SUCCESS_CREATED_INPUT);
  }

  private showErrorMsgAccordingToId(idInput: number, errorMsg: string): void {
    idInput ? this.message.showError(MensagensInsumoUtil.ERROR_UPDATE, errorMsg)
      : this.message.showError(MensagensInsumoUtil.ERROR_CREATED, errorMsg);
  }

}
