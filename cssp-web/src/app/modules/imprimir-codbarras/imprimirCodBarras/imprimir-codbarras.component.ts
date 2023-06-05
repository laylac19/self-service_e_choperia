import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ProdutoModel} from "../../../model/produto.model";
import {Location} from "@angular/common";
import {ProdutoService} from "../../../shared/service/produto.service";
import * as jsBarcode from 'jsbarcode';
import { jsPDF } from 'jspdf';
import {Page} from "../../../shared/util/page";
import {InsumoListModel} from "../../../model/list/insumo-list.model";
import {finalize} from "rxjs";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {ImprimirCodBarrasTable} from "../imprimir-cod-barras-table";
import {FormGroup} from "@angular/forms";
import {Router} from "@angular/router";

interface produtoImprimirCodBarras extends ProdutoModel {
  quantidade?: number;
}
@Component({
  selector: 'app-imprimir-codbarras',
  templateUrl: './imprimir-codbarras.component.html',
  styleUrls: ['./imprimir-codbarras.component.scss']
})
export class ImprimirCodbarrasComponent implements OnInit{


  columns: ColumnUtil[] = ImprimirCodBarrasTable.PRODUCTS_TABLE;
  produtos: produtoImprimirCodBarras[] = [];
  entryList: Page<InsumoListModel[]> | any = new Page<InsumoListModel[]>();
  rows: 5;
  paginator: true;
  formGroup: FormGroup;

  selectedProducts: produtoImprimirCodBarras[] = [];
  printingBarcodeMode = false;



  @BlockUI() blockUI: NgBlockUI;
  ELEMENT_DATA: any[] = [];

  produto: produtoImprimirCodBarras = {
    qtdeEstoque: 0,
    quantidade: 0,
    codigoBarras: '0',
    descricao: '',
    pontoEncomenda: 0,
    precoCompra: 0,
    unidade: '',
    precoVenda: 0.0,
    id: 0,
    etiquetaRFID: ``,
    litro_chope: 0
  };

  constructor(
    private service: ProdutoService,
    private location: Location,
    private inputService: ProdutoService,
    private router: Router
  ) {

  }

  ngOnInit() {
    this.fetchAll();
  }

  reset(): void {
    // this.ELEMENT_DATA = [];
  }

  fetchAll(): void {
    this.inputService.listAllInputs()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          console.log(MensagensProntasUtil.ERROR, MensagensProntasUtil.ERROR);
        }
      })
  }

  private resultRequestList(result: Page<InsumoListModel[]>): void {
    result.content ? this.entryList = result : this.entryList = [];
  }

  imprimirBarCodes(): void {
    debugger
    const canvas = document.createElement('canvas');
    const pdf = new jsPDF('l', 'mm', [115, 23]);
    const spacingBetweenTickets = 1.3;
    const barcodeSize = 36.6;
    let positionBuffer = spacingBetweenTickets;

    this.selectedProducts.forEach((product) => {
      if (!product.codigoBarras) {
        return;
      }
      if (!product.quantidade) {
        product.quantidade = 1;
      }

      for (let i = 0; i < product.quantidade; i++) {
        if (positionBuffer >= 110) {
          pdf.addPage();
          positionBuffer = spacingBetweenTickets;
        }

        jsBarcode(canvas, product.codigoBarras!);
        pdf.addImage(canvas.toDataURL('image/jpeg'), 'JPEG', positionBuffer, 0, barcodeSize, 20.4);
        positionBuffer += barcodeSize + spacingBetweenTickets;
      }
    });

    pdf.autoPrint();
    pdf.output('dataurlnewwindow');
  }

  backToProductPage(): void{
    this.router.navigate(['Produtos']);
    if(localStorage.getItem('roleDescription') != 'Administrador'){
      localStorage.setItem('impBarCode', '');
      location.reload();
    }
  }


  // togglePrintingBarcodeMode(): void {
  //   this.selectedProducts = [];
  //   this.products.forEach(value => (value as productBarcodePrint).amount = undefined);
  //   this.printingBarcodeMode = !this.printingBarcodeMode;
  // }
}
