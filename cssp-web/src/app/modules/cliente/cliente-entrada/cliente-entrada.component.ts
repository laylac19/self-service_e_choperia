import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ClienteModel} from "../../../model/cliente.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ClienteService} from "../../../shared/service/cliente.service";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {MensagensClienteUtil} from "../util/mensagens-cliente.util";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";

@Component({
  selector: 'app-cliente-entrada',
  templateUrl: './cliente-entrada.component.html',
  styleUrls: ['./cliente-entrada.component.scss']
})
export class ClienteEntradaComponent implements OnInit {

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;

  customer: ClienteModel;
  list: boolean = false;

  constructor(private builder: FormBuilder,
              private customerService: ClienteService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.assignRFIDCardForm();
  }

  assignRFIDCardForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      nome: [null],
      numCartaoRFID: [null],
      telefone: [null],
      email: [null],
      cpf: [null]
    });
  }

  findCustomerByID(id: any): void {
    this.customerService.findById(id).subscribe({
      next: (response: ClienteModel) => {
        this.customer = response;
        this.formGroup.patchValue(response);
      },
    })
  }

  saveForm(): void {
    this.customer = this.formGroup.getRawValue();
    this.customerService.customerEntry(this.customer)
      .subscribe({
        next: () => {
          this.showSuccessMsg(this.customer.numCartaoRFID);
          this.closeForm();
          this.list = true;
        },
        error: () => {
          this.message.showInfo(MensagensProntasUtil.SUB_MESSAGE_ERROR, MensagensProntasUtil.ERROR);
        }
      });
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
  }

  private showSuccessMsg(card: string): void {
    card ? this.message.showSuccess(MensagensClienteUtil.SUCCESS_ENTRY)
      : this.message.showSuccess(MensagensClienteUtil.CARD_READ_ERROR);
  }
}
