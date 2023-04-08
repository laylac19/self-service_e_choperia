import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {Page} from "../../../shared/util/page";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ProdutoColumnUtil} from "../util/produto-column.util";
import {InsumoListModel} from "../../../model/list/insumo-list.model";
import {InsumoProdComponent} from "../insumo-prod/insumo-prod.component";
import {InsumoService} from "../../../shared/service/insumo.service";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {finalize} from "rxjs";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {EntidadeUtil} from "../../../shared/util/entidade.util";
import {TituloModalInsumoUtil} from "../util/modal/titulo-modal-insumo.util";

@Component({
  selector: 'app-insumo-list',
  templateUrl: './insumo-list.component.html',
  styleUrls: ['./insumo-list.component.scss']
})
export class InsumoListComponent implements OnInit {

  columns: ColumnUtil[] = ProdutoColumnUtil.INPUT_COLUMNS;
  inputsList: Page<InsumoListModel[]> | any = new Page<InsumoListModel[]>();

  titleDialog: string

  @BlockUI() blockUI: NgBlockUI;
  @Input() display = false;
  @ViewChild(InsumoProdComponent) inputFormComponent: InsumoProdComponent;

  constructor(private inputService: InsumoService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.listAllInputs();
  }

  listAllInputs(): void {
    this.blockUI.start();
    this.inputService.findAll()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          this.message.showInfo(MensagensProntasUtil.ERROS_LIST_ALL, MensagensProntasUtil.ERROR);
        }
      })
  }

  private resultRequestList(result: Page<InsumoListModel[]>): void {
    result.content ? this.inputsList = result : this.inputsList = [];
  }

  registerInput() {
    this.titleDialog = TituloModalInsumoUtil.setTitulo(TituloModalInsumoUtil.NEW.index).header;
    this.inputFormComponent.formGroup.reset();
    this.display = true;
  }

  giveEntryToProduct(): void {

  }

  outputInProduct(): void {

  }

  printBarcode(): void {

  }

  onSaveRegister(): void {
    this.inputFormComponent.saveInputForm();
    this.listAllInputs();
    this.onClose();
  }

  editInput(id: number): void {
    this.titleDialog = TituloModalInsumoUtil.setTitulo(TituloModalInsumoUtil.EDIT.index).header;
    this.display = true;
    this.inputFormComponent.editInput(id);
  }

  deactivateUser(id: number): void {
    this.inputService.delete(id).subscribe(() => this.listAllInputs());
  }

  confirmAction(id: number): void {
    this.message.confirmarDialog(id, () => this.deactivateUser(id), EntidadeUtil.USUARIO)
  }

  onClose(): void {
    this.display = false;
    this.inputFormComponent.formGroup.reset();
  }

}
