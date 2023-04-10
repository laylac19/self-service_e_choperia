import {Component, OnInit, Renderer2} from '@angular/core';
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CaixaConsumeTable} from "../caixa-consume-table";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {ClienteModel} from "../../../model/cliente.model";
import {ClienteService} from "../../../shared/service/cliente.service";
import {CaixaService} from "../../../shared/service/caixa.service";
import {finalize, map, switchMap} from "rxjs";
import {ClienteCompraService} from "../../../shared/service/cliente-compra.service";
import {ClienteCompraModelList} from "../../../model/list/cliente-compra-list.model";

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
    this.clienteService.findById(Number(event.target.value))
      .pipe(
        switchMap((cliente) => {
          return this.clienteCompraService.findByRFID(cliente.numCartaoRFID).pipe(
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
    return compras;
  }

  get totalCompra(): number {
    return this.clientesCompras.reduce((sum, compra) => {
      return sum + compra.valor;
    }, 0);
  }

  finalizeOrder(){
    const idResponsavel = this.clientes[0].id;
    this.caixaService.insert({
      idClienteResponsavelCompra: idResponsavel,
      idClientesConjuntos: this.clientes.filter(cliente => cliente.id != idResponsavel).map(cliente => cliente.id)
    })
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
    const printContents = this.renderer.selectRootElement('#consumidos')!.innerHTML;
    const originalContents = document.body.innerHTML;
    document.body.innerHTML = printContents;
    window.print();
    window.onafterprint = (event) => {
      console.log("teste")
    }
    document.body.innerHTML = originalContents;

  }

}
