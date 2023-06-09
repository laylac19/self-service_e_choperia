import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ColumnUtil} from "../../../../shared/util/columnUtil";
import {Page} from "../../../../shared/util/page";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ProdutoColumnUtil} from "../../util/produto-column.util";
import {InsumoListModel} from "../../../../model/list/insumo-list.model";
import {InsumoProdComponent} from "../insumo-prod/insumo-prod.component";
import {ProdutoService} from "../../../../shared/service/produto.service";
import {MensagensConfirmacao} from "../../../../shared/util/msgConfirmacaoDialog.util";
import {finalize} from "rxjs";
import {MensagensProntasUtil} from "../../../../shared/util/messages/MensagensProntas.util";
import {EntidadeUtil} from "../../../../shared/util/entidade.util";
import {TituloModalInsumoUtil} from "../../util/modal/titulo-modal-insumo.util";
import {InsumoEntryComponent} from "../insumo-entry/insumo-entry.component";
import {InsumoWithdrawComponent} from "../insumo-withdraw/insumo-withdraw.component";
import {ImprimirCodbarrasComponent} from "../../../imprimir-codbarras/imprimirCodBarras/imprimir-codbarras.component";
import {Router} from "@angular/router";

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
  @ViewChild(ImprimirCodbarrasComponent) imprimirCodbarrasComponent: ImprimirCodbarrasComponent;

  constructor(private inputService: ProdutoService,
              private message: MensagensConfirmacao,
              private router: Router) {
  }

  ngOnInit(): void {
    this.fetchAll();
  }

  fetchAll(): void {
    this.blockUI.start();
    this.inputService.listAllInputs()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          this.message.showInfo(MensagensProntasUtil.SUB_MESSAGE_ERROR, MensagensProntasUtil.ERROR);
        }
      });
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
    this.router.navigate(['ImprimirBarCode']);
  }

  onSaveRegister(): void {
    this.inputFormComponent.saveInputForm();
    this.onClose();
  }

  callEntryQtd(): void {
    this.inputService.enterProductInput(
      this.entryFormComponent.entryList.map((product) => ({id: product.id, qtdeEstoque: product.qtdeEstoque}))
    ).subscribe(
      () => {
        this.onCloseEntry();
        this.message.showSuccess("Quantidade de estoque atualizada!");
      },
      () => {
        this.message.showError("Falha ao atualizar estoque!", "Error");
      }
    );
  }

  callWithdrawQtd(): void {
    this.inputService.releaseProductOutput(
      this.withdrawFormComponent.withdrawList.map((product) => ({id: product.id, qtdeEstoque: product.qtdeEstoque}))
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
    this.updateListAfterCreate();
    this.inputFormComponent.formGroup.reset();
  }

  onCloseEntry(): void {
    this.updateListAfterEntry();
    this.entryFormComponent.reset();
  }

  onCloseWithdraw(): void {
    this.updateListAfterWithdraw();
    this.withdrawFormComponent.reset();
  }

  private listAllInputs(): void {
    this.inputService.listAllInputs().subscribe((resp) => {
      this.resultRequestList(resp)
    });
  }

  private resultRequestList(result: Page<InsumoListModel[]>): void {
    result.content ? this.inputsList = result : this.inputsList = [];
  }

  private updateListAfterCreate() {
    if (this.inputFormComponent.list) {
      this.listAllInputs();
    }
    this.display = false;
  }

  private updateListAfterEntry() {
    if (this.entryFormComponent.entryList) {
      this.listAllInputs();
    }
    this.displayEntry = false;
  }

  private updateListAfterWithdraw() {
    if (this.withdrawFormComponent.withdrawList) {
      this.listAllInputs();
    }
    this.displayWithdraw = false;
  }

}
