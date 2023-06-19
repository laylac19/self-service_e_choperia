import {Component, EventEmitter, OnInit, Output, Renderer2} from '@angular/core';
import {ClienteModel} from "../../../model/cliente.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ClienteService} from "../../../shared/service/cliente.service";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {MensagensClienteUtil} from "../util/mensagens-cliente.util";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";

@Component({
  selector: 'app-verifica-rfid',
  templateUrl: './verifica-rfid.component.html',
  styleUrls: ['./verifica-rfid.component.scss']
})
export class VerificaRfid implements OnInit {

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;

  customer: ClienteModel;
  list: boolean = false;

  constructor(private builder: FormBuilder,
              private customerService: ClienteService,
              private message: MensagensConfirmacao,
              private renderer: Renderer2) {
  }

  ngOnInit(): void {
    this.assignRFIDCardForm();
  }

  assignRFIDCardForm(): void {
    this.formGroup = this.builder.group({
      numCartaoRFID: [null],
    });
  }

  findCustomerByRfid(event: any): void {
    const rfid = event.target.value
    this.customerService.findClienteByNumCartaoRFID(rfid).subscribe({
      next: (response) => {
        console.log(response)
        if(response){
          this.showErrorMsg();
          this.formGroup.reset();
          this.renderer.selectRootElement('#numCartaoRFID').focus();
          console.log("Esta devendo");
        }
        else{
          this.showSuccessMsg();
          this.formGroup.reset();
          this.renderer.selectRootElement('#numCartaoRFID').focus();
          console.log("liberado");
        }
      },
    })
  }

  saveForm(): void {
    this.customer = this.formGroup.getRawValue();
    this.customerService.customerEntry(this.customer)
      .subscribe({
        next: () => {
          this.showSuccessMsg();
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

  private showSuccessMsg(): void {
   this.message.showSuccess(MensagensClienteUtil.PAYMENT_MADE);
  }

  private showErrorMsg(): void{
    this.message.showError(MensagensClienteUtil.PENDING_PAYMENT, "Encaminhe o Cliente para o Caixa")
  }
}
