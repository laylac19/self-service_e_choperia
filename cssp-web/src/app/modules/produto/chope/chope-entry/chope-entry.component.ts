import {Component, Renderer2} from '@angular/core';
import {ColumnUtil} from "../../../../shared/util/columnUtil";
import {ProdutoColumnUtil} from "../../util/produto-column.util";
import {ProdutoModel} from "../../../../model/produto.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../../shared/util/msgConfirmacaoDialog.util";
import {finalize} from "rxjs";
import {ChopeService} from "../../../../shared/service/chope.service";

@Component({
  selector: 'app-chope-entry',
  templateUrl: './chope-entry.component.html',
  styleUrls: ['./chope-entry.component.scss']
})
export class ChopeEntryComponent {
  columns: ColumnUtil[] = ProdutoColumnUtil.ENTRY_DRAFF_BEER_COLUMNS;
  entryList: ProdutoModel[] = [];
  formGroup: FormGroup;

  ngOnInit(): void {
    this.newForm();
  }

  constructor(private builder: FormBuilder,
              private draftBeerService: ChopeService,
              private message: MensagensConfirmacao,
              private renderer: Renderer2) {
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      etiquetaRFID: [null, [Validators.required]]
    });
  }

  addProductOnTable(event: any){
    this.draftBeerService.findDraftBeerByRFID(event.target.value)
      .pipe(
        finalize(() => {
          this.formGroup.reset();
          this.renderer.selectRootElement('#etiquetaRFID').focus();
        })
      )
      .subscribe(
        (beer) => {
          if (beer) {
            let index = this.entryList.findIndex((resp) => resp.id === beer.id);
            if (index < 0) {
              let newDraftBeer: any = {
                ...beer,
                litro_chope: 100,
                qtdeEstoque: 1
              }
              this.entryList = [...this.entryList, newDraftBeer];
            } else {
              this.entryList = this.entryList.map((product) => {
                if (product.id === beer.id) {
                  product.qtdeEstoque = product.qtdeEstoque + 1;
                  product.litro_chope = product.litro_chope + 100;
                }
                return product;
              })
            }
          } else {
            this.message.showError("Chope não encontrado!", "Erro");
          }
        },
        () => {
          this.message.showError("Chope não encontrado!", "Erro");
        }
      )
  }

  reset(): void {
    this.entryList = [];
    this.formGroup.reset();
  }

}
