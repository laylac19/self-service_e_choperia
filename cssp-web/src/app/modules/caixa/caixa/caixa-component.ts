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
  listRfidClientes: string[] = []
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
      codCartaoCliente: [null, [Validators.required]],
      desconto: [null],
      metodoPagamento: [null]
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
            this.listRfidClientes = [...this.listRfidClientes, cliente.numCartaoRFID];
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
    let order: CaixaModel = {
      id: null,
      desconto: 0.0,
      formaPagamento: 'PIX',
      listCodRfid: this.listRfidClientes,
      valorFinal: this.totalCompra,
      idClientePrincipal: idResponsavel,
      totalConta: this.totalCompra,
      idCompra: this.clientes[0].compras[0].idCompra,
    }
    this.caixaService.insert(order)
      .subscribe(
        () => {
          this.imprimirComprovante()
          this.clientes = [];
          this.nomesClientes = [];
          this.renderer.selectRootElement('#codCartaoCliente').focus();
          this.message.showSuccess("Compra finalizada com sucesso!");
        }
      )
  }

   // imprimir comprovante

  imprimirComprovante(){
    const mywindow = window.open('', 'PRINT', 'height=600,width=600');

    mywindow?.document.write('<html><head><title>' + "CSSP - Choperia" + '</title>');
    mywindow?.document.write('<head><body style="margin: 0; display: grid; font-family:Arial, Helvetica, sans-serif; font-size: 14px"></div> </body></html>')

    const divPrincipal = this.criarDivPrincipal();

    divPrincipal.appendChild(this.criarCabecalhoComprovante());
    divPrincipal.appendChild(this.criarDivNomeCliente());
    divPrincipal.appendChild(this.criarTabelaItensConsumidos());
    divPrincipal.appendChild(this.criarDivValorTotal());
    divPrincipal.appendChild(this.criarDivQtdItens());
    //divPrincipal.appendChild(this.criarDivMetodoPagamento());
    mywindow?.document.body.appendChild(divPrincipal);

    setTimeout(() => {
      mywindow?.document.close();
      mywindow?.focus();
      mywindow?.print();
      mywindow?.close();
    }, 200);

  }

  private criarDivPrincipal(): HTMLElement{
    let div = document.createElement('div');
    div.style.maxWidth = '8cm';
    return div;
  }

  private criarCabecalhoComprovante(): HTMLElement {
    let div = document.createElement('div');

    div.innerHTML = `
      <p style="text-align: center; font-weight: bold">${new Date().toLocaleDateString()} ${new Date().getHours().toLocaleString()}:${new Date().getMinutes().toLocaleString()}:${new Date().getSeconds().toLocaleString()}</p>
      <h3 style="text-align: center; font-family:Arial, Helvetica, sans-serif;">CSSP - Choperia</h3>
      <hr>
    `;
    return div;
  }

  private criarDivValorTotal(): HTMLElement {
    let div = document.createElement('div');

    div.innerHTML = `
     <hr>
     <table style="width: 100%; font-size: 12px">
        <tr>
            <td colspan="3">Valor total:</td>
            <td style="text-align: right">${this.numeroParaReal(this.totalCompra)}</td>
        </tr>
     </table>
     `;
    return div;
  }

  criarTabelaItensConsumidos(): HTMLElement {
    const table = document.createElement('table');
    const head = document.createElement('thead');
    const linha = document.createElement('tr');
    const colunaItem = document.createElement('td');
    const colunaPreco = document.createElement('td');

    table.style.width = '100%';
    table.style.fontSize = '8px';

    colunaItem.innerHTML = 'Nome';
    colunaItem.style.textAlign = 'left';

    colunaPreco.innerHTML = 'Preço';
    colunaPreco.style.textAlign = 'left';

    linha.append(colunaItem, colunaPreco);
    head.append(linha);
    table.append(head);
    table.appendChild(this.criarLinhasTabelaItensConsumidos());
    return table;
  }

  criarLinhasTabelaItensConsumidos(): HTMLElement{
    const tbody = document.createElement('tbody');
    this.clientes?.forEach(cliente => {
      cliente.compras.forEach(compra => {
        const tr = document.createElement('tr')
        const colunaItem = document.createElement('td');
        const colunaPreco = document.createElement('td');
        colunaItem.innerHTML = <string>compra.descricao;
        colunaPreco.innerHTML = this.numeroParaReal(compra.valor)
        tr.appendChild(colunaItem);
        tr.appendChild(colunaPreco);
        tbody.appendChild(tr);
      })
    });
    return tbody;
  }

  numeroParaReal(n: any){
    return n.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'})
  }

  criarDivMetodoPagamento(): HTMLElement {
    let div = document.createElement('div');

    // div.innerHTML = `
    //  <table style="width: 100%; font-size: 12px">
    //      <tr>
    //         <td colspan="3">Método de pagamento:</td>
    //         <td style="text-align: right">${this.verificarMetodoPagamento(this.formGroup.get('metodoPagamento')?.value)}</td>
    //     </tr>
    //  </table>
    //  <hr>
    //  `;
    return div;
  }
  private criarDivNomeCliente(): HTMLElement {
    let div = document.createElement('div');

    div.innerHTML = `
     <ht>
     <table style="width: 100%; font-size: 12px">
         <tr>
            <td colspan="3">Nome do cliente:</td>
            <td style="text-align: right">${this.nomesClientes[0]}</td>
        </tr>
     </table>
     <hr>
     `;
    return div;
  }

  private criarDivQtdItens(): HTMLElement {
    let div = document.createElement('div');

    div.innerHTML = `
     <ht>
     <table style="width: 100%; font-size: 12px">
         <tr>
            <td colspan="3">Qtd. Itens:</td>
            <td style="text-align: right">${this.clientesCompras.length}</td>
        </tr>
     </table>
     <hr>
     `;
    return div;
  }
}
