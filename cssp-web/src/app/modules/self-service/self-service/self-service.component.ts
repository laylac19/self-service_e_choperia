import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {SelfServiceCompraModel} from "../../../model/self-service-compra.model";
import {SelfServiceService} from "../../../shared/service/self-service.service";
import {MensagensUsuarioUtil} from "../../usuario/util/mensagens-usuario.util";
import {ClienteService} from "../../../shared/service/cliente.service";
import {ClienteModel} from "../../../model/cliente.model";
import {MensagensClienteUtil} from "../../cliente/util/mensagens-cliente.util";

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
              private ssBuyService: SelfServiceService,

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
    // this.ssBuyService.insert(this.selfServiceBuy)
    //   .subscribe({
    //     next: () => {
    //       this.showSuccessMsgAccordingToId(this.selfServiceBuy.id);
    //       this.closeForm();
    //       this.list = true;
    //     },
    //     error: (error) => {
    //       this.showErrorMsgAccordingToId(this.selfServiceBuy.id, error.message);
    //     }
    //   });
    console.log(this.selfServiceBuy);
    //reset;
  }

  private showSuccessMsgAccordingToId(idUser: number): void {
    idUser ? this.message.showSuccess(MensagensUsuarioUtil.UPDATE_SUCCESSFUL_USER)
      : this.message.showSuccess(MensagensUsuarioUtil.SUCCESS_CREATED_USER);
  }

  findClienteByRFID(event: any): void {
    console.log(event)
    this.customerService.findById(event.target.value).subscribe({
      next: (response) => {
        console.log(response);
        this.showMsgAccordingToValidatingAnswer(response);
        this.formGroup.get('idCliente')?.setValue(response.numCartaoRFID);
        this.formGroup.get('nomeClinte')?.setValue(response.nome);
      },
    });
  }

  private showMsgAccordingToValidatingAnswer(response: ClienteModel): void {
    response ? this.message.showSuccess(MensagensClienteUtil.CUSTOMER_FOUND)
      : this.message.showSuccess(MensagensClienteUtil.CUSTOMER_NOT_FOUND);
  }

  setPurchaseValue(event: any): void {
    const peso: number = 14.99;
    const total: number = peso * event.target.value;
    this.formGroup.get('peso')?.setValue(peso);
    this.formGroup.get('valorCompra')?.setValue(total);
  }
}
