import {Component, OnInit, Renderer2} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {ProdutoService} from "../../../shared/service/produto.service";
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {ProdutoColumnUtil} from "../util/produto-column.util";
import {finalize} from "rxjs";
import {ProdutoModel} from "../../../model/produto.model";

@Component({
  selector: 'app-insumo-entry',
  templateUrl: './insumo-entry.component.html',
  styleUrls: ['./insumo-entry.component.scss']
})
export class InsumoEntryComponent implements OnInit {
  columns: ColumnUtil[] = ProdutoColumnUtil.ENTRY_PRODUCTS_COLUMNS;
  entryList: ProdutoModel[] = [];
  formGroup: FormGroup;

  ngOnInit(): void {
    this.newForm();
  }

  constructor(private builder: FormBuilder,
              private entryInsumoService: ProdutoService,
              private message: MensagensConfirmacao,
              private renderer: Renderer2) {
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      codigoBarras: [null, [Validators.required]]
    });
  }

  addProductOnTable(event: any){
    this.entryInsumoService.findInputByBarCode(event.target.value)
      .pipe(
        finalize(() => {
          this.formGroup.reset();
          this.renderer.selectRootElement('#codBarras').focus();
        })
      )
      .subscribe(
        (insumo) => {
          if (insumo) {
            let index = this.entryList.findIndex((ins) => ins.id === insumo.id);
            if (index < 0) {
              let newInsumo: any = {
                ...insumo,
                qtdeEstoque: 1
              }
              this.entryList = [...this.entryList, newInsumo];
            } else {
              this.entryList = this.entryList.map((product) => {
                if (product.id === insumo.id) {
                  product.qtdeEstoque++;
                }
                return product;
              })
            }
          } else {
            this.message.showError("Produto não encontrado!", "Erro");
          }
        },
        () => {
          this.message.showError("Produto não encontrado!", "Erro");
        }
      )
  }

  reset(): void {
    this.entryList = [];
    this.formGroup.reset();
  }

}
