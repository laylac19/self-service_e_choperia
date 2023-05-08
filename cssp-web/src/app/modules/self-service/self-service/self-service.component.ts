import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {MensagensUsuarioUtil} from "../../usuario/util/mensagens-usuario.util";
import {ClienteService} from "../../../shared/service/cliente.service";
import {ClienteModel} from "../../../model/cliente.model";
import {MensagensClienteUtil} from "../../cliente/util/mensagens-cliente.util";
import {ClienteCompraService} from "../../../shared/service/cliente-compra.service";
import {ClienteCompraProdutoModel} from "../../../model/cliente-compra-produto.model";
import {ProdutoService} from "../../../shared/service/produto.service";
import {ProdutoModel} from "../../../model/produto.model";
import {MensagensSelfServiceUtil} from "../../produto/util/messages/mensagens-self-service.util";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {InsumoProdComponent} from "../../produto/insumo/insumo-prod/insumo-prod.component";
import {ReposicaoSelfServiceComponent} from "../reposicao-self-service/reposicao-self-service.component";

@Component({
  selector: 'app-self-service',
  templateUrl: './self-service.component.html',
  styleUrls: ['./self-service.component.scss']
})
export class SelfServiceComponent implements OnInit {

  formGroup: FormGroup;
  selfServiceBuy: ClienteCompraProdutoModel;
  seflService: ProdutoModel;

  titleDialog: string;
  list: boolean = false;

  @BlockUI() blockUI: NgBlockUI;
  @Input() display = false;
  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();
  @ViewChild(ReposicaoSelfServiceComponent) notifyComponent: ReposicaoSelfServiceComponent;


  constructor(private builder: FormBuilder,
              private ssBuyService: ClienteCompraService,
              private customerService: ClienteService,
              private productService: ProdutoService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
    this.findSelfServiceProduct();
    //setInterval(this.getWeight, 1000)
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      idCliente: [null, Validators.required],
      idProduto: [null, Validators.required],
      nomeClinte: [null],
      numCartaoRFID: [null],
      valorCompra: [null, Validators.required],
      pesoPrato: [null, Validators.required],
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

  getWeight() {
    this.productService.getWeightValue().subscribe(response => {
        if(response){
          this.formGroup.setValue({pesoPrato: response})
          console.log(response + ' PESO')
        }else{
          this.formGroup.setValue({pesoPrato: 0.0})
        }
    })
    console.log("chamando")
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
    this.formGroup.get('idProduto')?.setValue(this.seflService.id);

    const peso: number = event.target.value;
    this.formGroup.get('pesoPrato')?.setValue(peso);

    const total: number = this.seflService.precoVenda * peso;
    this.formGroup.get('valorCompra')?.setValue(total.toPrecision(4));
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
  }

  private findSelfServiceProduct(): void {
    this.productService.findSelfServiceProduct().subscribe((resp: ProdutoModel)=> {
      this.seflService = resp;
    });
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

  requestReplacement() {
    this.titleDialog = "SOLICITAR REPOSIÇÃO";
    this.notifyComponent.formGroup.reset();
    this.display = true;
  }

  onRequestSent() {
    this.notifyComponent.sendRequestDish();
    this.display = false;
  }

  onCloseRequest() {
    this.display = false;
    this.notifyComponent.formGroup.reset();
  }
}
