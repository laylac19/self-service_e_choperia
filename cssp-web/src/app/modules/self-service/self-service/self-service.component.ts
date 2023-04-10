import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {SelfServiceCompraModel} from "../../../model/self-service-compra.model";
import {MensagensUsuarioUtil} from "../../usuario/util/mensagens-usuario.util";
import {ClienteService} from "../../../shared/service/cliente.service";
import {ClienteModel} from "../../../model/cliente.model";
import {MensagensClienteUtil} from "../../cliente/util/mensagens-cliente.util";
import {SelfServiceCompraService} from "../../../shared/service/self-service-compra.service";
import {MensagensSelfServiceUtil} from "../../produto/util/messages/mensagens-self-service.util";

@Component({
  selector: 'app-self-service',
  templateUrl: './self-service.component.html',
  styleUrls: ['./self-service.component.scss']
})
export class SelfServiceComponent implements OnInit {

  formGroup: FormGroup;
  selfServiceBuy: SelfServiceCompraModel;

  list: boolean = false;
  nameCustomer: string;

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();


  constructor(private builder: FormBuilder,
              private ssBuyService: SelfServiceCompraService,
              private customerService: ClienteService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      idCliente: [null, Validators.required],
      nomeClinte: [null],
      numCartaoRFID: [null],
      valorCompra: [null, Validators.required],
      peso: [null, Validators.required],
    });
  }

  saveForm(): void {
    this.selfServiceBuy = this.formGroup.getRawValue();
    this.ssBuyService.save(this.selfServiceBuy)
      .subscribe({
        next: () => {
          this.message.showSuccess(MensagensSelfServiceUtil.PURCHASE_MADE_SUCCESSFUL);
          this.closeForm();
          this.list = true;
        },
        error: (error) => {
          this.showErrorMsgAccordingToId(this.selfServiceBuy.id, error.message);
        }
      });
  }

  findClienteByRFID(event: any): void {
    this.customerService.findClienteByNumCartaoRFID(event.target.value).subscribe({
      next: (response) => {
        this.showMsgAccordingToValidatingAnswer(response);
        this.fillInCustomerData(response);
      },
    });
  }

  setPurchaseValue(event: any): void {
    const peso: number = 14.99;
    const total: number = peso * event.target.value;
    this.formGroup.get('peso')?.setValue(peso);
    this.formGroup.get('valorCompra')?.setValue(total);
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
  }

  private showMsgAccordingToValidatingAnswer(response: ClienteModel): void {
    response ? this.message.showSuccess(MensagensClienteUtil.CUSTOMER_FOUND)
      : this.message.showSuccess(MensagensClienteUtil.CUSTOMER_NOT_FOUND);
  }

  private showErrorMsgAccordingToId(idUser: number, errorMsg: string): void {
    idUser ? this.message.showError(MensagensUsuarioUtil.ERROR_UPDATE, errorMsg)
      : this.message.showError(MensagensUsuarioUtil.ERROR_CREATED, errorMsg);
  }
  private fillInCustomerData(response: ClienteModel) {
    this.formGroup.get('idCliente')?.setValue(response.id);
    this.formGroup.get('nomeClinte')?.setValue(response.nome);
    this.formGroup.get('numCartaoRFID')?.setValue(response.numCartaoRFID);
  }

}
