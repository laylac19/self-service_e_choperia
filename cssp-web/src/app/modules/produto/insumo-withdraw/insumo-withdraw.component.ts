import {Component, EventEmitter, Input, OnInit, Output, Renderer2} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {InsumoWithdrawModel} from "../../../model/insumo-withdraw.model";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {InsumoService} from "../../../shared/service/insumo.service";
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {ProdutoColumnUtil} from "../util/produto-column.util";
import {Page} from "../../../shared/util/page";
import {InsumoWithdrawListModel} from "../../../model/list/insumo-withdraw-list.model";
import {InsumoModel} from "../../../model/insumo.model";
import {finalize} from "rxjs";

@Component({
  selector: 'app-insumo-withdraw',
  templateUrl: './insumo-withdraw.component.html',
  styleUrls: ['./insumo-withdraw.component.scss']
})
export class InsumoWithdrawComponent implements OnInit {

  columns: ColumnUtil[] = ProdutoColumnUtil.WITHDRAW_PRODUCTS_COLUMNS;
  withdrawList: InsumoModel[] = [];
  formGroup: FormGroup;

  ngOnInit(): void {
    this.newForm();
  }

  constructor(private builder: FormBuilder,
              private withdrawInsumoService: InsumoService,
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
    this.withdrawInsumoService.findByBarCode(event.target.value)
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
