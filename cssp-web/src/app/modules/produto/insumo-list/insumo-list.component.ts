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
import {InsumoEntryComponent} from "../insumo-entry/insumo-entry.component";
import {InsumoWithdrawComponent} from "../insumo-withdraw/insumo-withdraw.component";

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
  @Input() displayEntry = false;
  @Input() displayWithdraw = false;
  @ViewChild(InsumoProdComponent) inputFormComponent: InsumoProdComponent;
  @ViewChild(InsumoEntryComponent) entryFormComponent: InsumoEntryComponent;
  @ViewChild(InsumoWithdrawComponent) withdrawFormComponent: InsumoWithdrawComponent;
  constructor(private inputService: InsumoService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.listAllInputs();
  }

  listAllInputs(): void {
    this.blockUI.start();
    this.fetchAll();
  }

  fetchAll(): void {
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
    this.titleDialog = TituloModalInsumoUtil.setTitulo(TituloModalInsumoUtil.ENTRY.index).header;
    this.entryFormComponent.formGroup.reset();
    this.displayEntry = true;
  }

  outputInProduct(): void {
    this.titleDialog = TituloModalInsumoUtil.setTitulo(TituloModalInsumoUtil.WITHDRAW.index).header;
    this.inputFormComponent.formGroup.reset();
    this.displayWithdraw = true;
  }

  printBarcode(): void {

  }

  onSaveRegister(): void {
    this.inputFormComponent.saveInputForm();
    this.listAllInputs();
    this.onClose();
  }

  callEntryQtd(): void {
    this.inputService.enterProductInput(
      this.entryFormComponent.entryList.map((product) => ({ id: product.id, qtdeEstoque: product.qtdeEstoque }))
    ).subscribe(
      () => {
        this.onCloseEntry();
        this.fetchAll();
        this.message.showSuccess("Quantidade de estoque atualizada!");
      },
      () => {
        this.message.showError("Falha ao atualizar estoque!", "Error");
      }
    );
  }

  callWithdrawQtd(): void {
    this.inputService.releaseProductOutput(
      this.withdrawFormComponent.withdrawList.map((product) => ({ id: product.id, qtdeEstoque: product.qtdeEstoque }))
    ).subscribe(
      () => {
        this.onCloseWithdraw();
        this.fetchAll();
        this.message.showSuccess("Quantidade de estoque atualizada!");
      },
      () => {
        this.message.showError("Falha ao atualizar estoque!", "Error");
      }
    );
  }

  editInput(id: number): void {
    this.titleDialog = TituloModalInsumoUtil.setTitulo(TituloModalInsumoUtil.EDIT.index).header;
    this.display = true;
    this.inputFormComponent.editInput(id);
  }

  deactivateUser(id: number): void {
    this.inputService.delete(id).subscribe(() => this.listAllInputs());
  }

  confirmAction(input: any): void {
    this.message.confirmarDialog(input.id, () => this.deactivateUser(input.id), EntidadeUtil.INSUMO, input.descricao)
  }

  onClose(): void {
    this.display = false;
    this.inputFormComponent.formGroup.reset();
  }

  onCloseEntry(): void {
    this.displayEntry = false;
    this.entryFormComponent.reset();
  }

  onCloseWithdraw(): void {
    this.displayWithdraw = false;
    this.withdrawFormComponent.reset();
  }

}
