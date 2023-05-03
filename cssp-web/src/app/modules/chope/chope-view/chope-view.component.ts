import {Component, OnInit} from '@angular/core';
import {ChopeViewListModel} from "../../../model/list/chope-view-list.model";
import {ChopeService} from "../../../shared/service/chope.service";
import {MensagensSelfServiceUtil} from "../../produto/util/messages/mensagens-self-service.util";
import {ClienteCompraProdutoModel} from "../../../model/cliente-compra-produto.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ClienteCompraService} from "../../../shared/service/cliente-compra.service";
import {ClienteService} from "../../../shared/service/cliente.service";
import {ProdutoService} from "../../../shared/service/produto.service";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {ClienteModel} from "../../../model/cliente.model";
import {MensagensClienteUtil} from "../../cliente/util/mensagens-cliente.util";
import {ProdutoModel} from "../../../model/produto.model";

@Component({
  selector: 'app-chope-view',
  templateUrl: './chope-view.component.html',
  styleUrls: ['./chope-view.component.scss']
})
export class ChopeViewComponent implements OnInit {
  formGroup: FormGroup;

  draftBeers: ChopeViewListModel[] = [];

  draftBeerBuy: ClienteCompraProdutoModel;
  client: ClienteModel | null;
  beer: ProdutoModel;
  display: boolean = false;
  titleDialog: string = 'COMPRA';

  constructor(private draftBeerService: ChopeService,
              private builder: FormBuilder,
              private ssBuyService: ClienteCompraService,
              private customerService: ClienteService,
              private productService: ProdutoService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
    this.listAllDraftBeers();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      idCliente: [null, Validators.required],
      idProduto: [null, Validators.required],
      numCartaoRFID: [null],
      valorCompra: [null, Validators.required],
      canecaML: [null, Validators.required]
    });
  }

  listAllDraftBeers(): void {
    this.draftBeerService.listAllDraftBeers()
      .subscribe(
        (result) => {
          this.resultRequestList(result);
        });
  }

  onSave(): void {
    this.draftBeerBuy = this.formGroup.getRawValue();
    this.ssBuyService.save(this.draftBeerBuy)
      .subscribe({
        next: () => {
          this.message.showSuccess(MensagensSelfServiceUtil.PURCHASE_MADE_SUCCESSFUL);
          this.onClose();
        },
        error: (error) => {
          console.log(error.message);
        }
      });
  }

  findClienteByRFID(event: any): ClienteModel | null {
    this.customerService.findClienteByNumCartaoRFID(event.target.value).subscribe({
      next: (response) => {
        this.showMsgAccordingToValidatingAnswer(response);
        this.fillInCustomerData(response);
        this.client = response;
      },
    });
    return this.client;
  }

  findBeer(id: number): void {
    this.productService.findById(id).subscribe({
      next: (response: ProdutoModel) => {
        this.beer = response;
        this.fillInPurchase(response)
      },
    });
  }

  private resultRequestList(result: ChopeViewListModel[]): void {
    result ? this.draftBeers = result : this.draftBeers = [];
  }

  private showMsgAccordingToValidatingAnswer(response: ClienteModel): void {
    response ? this.message.showSuccess(MensagensClienteUtil.CUSTOMER_FOUND)
      : this.message.showSuccess(MensagensClienteUtil.CUSTOMER_NOT_FOUND);
  }

  private fillInCustomerData(response: ClienteModel) {
    this.formGroup.get('idCliente')?.setValue(response.id);
    this.formGroup.get('numCartaoRFID')?.setValue(response.numCartaoRFID);
  }

  private fillInPurchase(response: ProdutoModel) {
    this.formGroup.get('idProduto')?.setValue(response.id);
    this.formGroup.get('valorCompra')?.setValue(response.precoVenda);
    this.formGroup.get('canecaML')?.setValue(500);
  }

  buyBeer(beer: any) {
    const idBeer = beer.id;
    this.findBeer(idBeer);
    this.display = true;
  }

  onClose() {
    this.display = false;
    this.client = null;
    this.formGroup.reset();
  }
}
