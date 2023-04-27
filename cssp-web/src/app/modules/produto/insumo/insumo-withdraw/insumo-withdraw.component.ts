import {Component, OnInit, Renderer2} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../../shared/util/msgConfirmacaoDialog.util";
import {ProdutoService} from "../../../../shared/service/produto.service";
import {ColumnUtil} from "../../../../shared/util/columnUtil";
import {ProdutoColumnUtil} from "../../util/produto-column.util";
import {ProdutoModel} from "../../../../model/produto.model";
import {finalize} from "rxjs";

@Component({
  selector: 'app-insumo-withdraw',
  templateUrl: './insumo-withdraw.component.html',
  styleUrls: ['./insumo-withdraw.component.scss']
})
export class InsumoWithdrawComponent implements OnInit {

  columns: ColumnUtil[] = ProdutoColumnUtil.WITHDRAW_PRODUCTS_COLUMNS;
  withdrawList: ProdutoModel[] = [];
  formGroup: FormGroup;

  ngOnInit(): void {
    this.newForm();
  }

  constructor(private builder: FormBuilder,
              private withdrawInsumoService: ProdutoService,
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
    this.withdrawInsumoService.findInputByBarCode(event.target.value)
      .pipe(
        finalize(() => {
          this.formGroup.reset();
          this.renderer.selectRootElement('#codBarras').focus();
        })
      )
      .subscribe(
        (insumo) => {
          if (insumo) {
            let index = this.withdrawList.findIndex((ins) => ins.id === insumo.id);
            if (index < 0) {
              let newInsumo: any = {
                ...insumo,
                qtdeEstoque: 1
              }
              this.withdrawList = [...this.withdrawList, newInsumo];
            } else {
              this.withdrawList = this.withdrawList.map((product) => {
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
    this.withdrawList = [];
    this.formGroup.reset();
  }
}
