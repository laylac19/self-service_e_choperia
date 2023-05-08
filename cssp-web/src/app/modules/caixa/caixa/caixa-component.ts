import {Component, OnInit, Renderer2} from '@angular/core';
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CaixaConsumeTable} from "../caixa-consume-table";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {ClienteModel} from "../../../model/cliente.model";
import {ClienteService} from "../../../shared/service/cliente.service";
import {CaixaService} from "../../../shared/service/caixa.service";
import {ClienteCompraService} from "../../../shared/service/cliente-compra.service";
import {ClienteCompraModelList} from "../../../model/list/cliente-compra-list.model";
import {finalize, map, switchMap} from "rxjs";
import {CaixaModel} from "../../../model/caixa-model";

@Component({
  selector: 'app-caixa',
  templateUrl: './caixa-component.html',
  styleUrls: ['./caixa-component.scss']
})
export class CaixaComponent implements OnInit{

  columns: ColumnUtil[] = CaixaConsumeTable.CONSUME_TABLE;
  clientes: ClienteModel[] = [];
  nomesClientes: string[] = [];
  formGroup: FormGroup;

  ngOnInit(): void {
    this.newForm();
  }

  constructor(private builder: FormBuilder,
              private clienteService: ClienteService,
              private clienteCompraService: ClienteCompraService,
              private caixaService: CaixaService,
              private message: MensagensConfirmacao,
              private renderer: Renderer2) {
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      codCartaoCliente: [null, [Validators.required]]
    });
  }

  addConsumeOnTable(event: any) {
    this.clienteService.findClienteByNumCartaoRFID(event.target.value)
      .pipe(
        switchMap((cliente) => {
          return this.clienteCompraService.listPurchasedItemsOfCustomer(cliente.numCartaoRFID).pipe(
            map((clienteCompras) => {
              return { ...cliente, compras: clienteCompras };
            })
          )
        }),
        finalize(() => {
          this.formGroup.reset();
          this.renderer.selectRootElement('#codCartaoCliente').focus();
        })
      )
      .subscribe(
        (cliente) => {
          const clientIndexFound = this.clientes.findIndex((c) => c.id === cliente.id);
          if (clientIndexFound < 0) {
            this.clientes = [...this.clientes, cliente];
            this.nomesClientes = [...this.nomesClientes, cliente.nome];
            console.log(this.clientes)
          } else {
            this.message.showWarn("O cliente já consta na lista!", "Atenção");
          }
        }
      );
  }

  get clientesCompras(): ClienteCompraModelList[] {
    let compras: ClienteCompraModelList[] = [];
    this.clientes.forEach(cliente => {
      cliente.compras?.forEach(compra => compras.push(compra));
    })
    console.log(this.clientes)
    return compras;
  }

  get totalCompra(): number {
    return this.clientesCompras.reduce((sum, compra) => {
      return sum + compra.valor;
    }, 0);
  }

  finalizeOrder(){
    const idResponsavel = this.clientes[0].id;
    let order: CaixaModel = {
      id: null,
      desconto: 0.0,
      formaPagamento: 'PIX',
      valorFinal: this.totalCompra,
      idClientePrincipal: idResponsavel,
      totalConta: this.totalCompra,
      idCompra: this.clientes[0].compras[0].idCompra,
    }
    //Adicionar aqui infos do dto Caixa
    // idClienteResponsavelCompra: idResponsavel,
    // idClientesConjuntos: this.clientes.filter(cliente => cliente.id != idResponsavel).map(cliente => cliente.id)
    this.caixaService.insert(order)
      .subscribe(
        () => {
          this.clientes = [];
          this.nomesClientes = [];
          this.renderer.selectRootElement('#codCartaoCliente').focus();
          this.message.showSuccess("Compra finalizada com sucesso!");
          this.onPrint();
        }
      )
  }

  onPrint(): void {
    const printContents = this.renderer.selectRootElement('#consumidos', true);
    document.body.innerHTML = printContents.innerHTML;
    window.print();
    location.reload();
    this.renderer.selectRootElement('#codCartaoCliente').focus();
   }

}
