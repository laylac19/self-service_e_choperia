import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ColumnUtil} from "../../../../shared/util/columnUtil";
import {ProdutoColumnUtil} from "../../util/produto-column.util";
import {Page} from "../../../../shared/util/page";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {MensagensConfirmacao} from "../../../../shared/util/msgConfirmacaoDialog.util";
import {ChopeListModel} from "../../../../model/list/chope-list.model";
import {ChopeProdComponent} from "../chope-prod/chope-prod.component";
import {ChopeService} from "../../../../shared/service/chope.service";
import {EntidadeUtil} from "../../../../shared/util/entidade.util";
import {finalize} from "rxjs";
import {MensagensProntasUtil} from "../../../../shared/util/messages/MensagensProntas.util";
import {TituloModalChopeUtil} from "../../util/modal/titulo-modal-chope.util";
import {ChopeEntryComponent} from "../chope-entry/chope-entry.component";
import {TituloModalInsumoUtil} from "../../util/modal/titulo-modal-insumo.util";
import {ProdutoService} from "../../../../shared/service/produto.service";

@Component({
  selector: 'app-chope-list',
  templateUrl: './chope-list.component.html',
  styleUrls: ['./chope-list.component.scss']
})
export class ChopeListComponent implements OnInit {

  columns: ColumnUtil[] = ProdutoColumnUtil.DRAFT_BEER_COLUMNS;
  draftBeersList: Page<ChopeListModel[]> | any = new Page<ChopeListModel[]>();

  titleDialog: string

  @BlockUI() blockUI: NgBlockUI;
  @Input() display = false;
  @Input() displayEntry = false;
  @ViewChild(ChopeProdComponent) draftBeerFormComponent: ChopeProdComponent;
  @ViewChild(ChopeEntryComponent) entryFormComponent: ChopeEntryComponent;

  constructor(private drafBeerService: ChopeService,
              private productService: ProdutoService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.findAllDraftBeers();
  }

  findAllDraftBeers(): void {
    this.blockUI.start();
    this.drafBeerService.findAll()
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

  registerDraftBeer() {
    this.titleDialog = TituloModalChopeUtil.setTitulo(TituloModalChopeUtil.NEW.index).header;
    this.draftBeerFormComponent.formGroup.reset();
    this.display = true;
  }

  giveEntryToProduct() {
    this.titleDialog = TituloModalInsumoUtil.setTitulo(TituloModalChopeUtil.ENTRY.index).header;
    this.entryFormComponent.formGroup.reset();
    this.displayEntry = true;
  }

  printRFIDtag() {

  }

  onSave() {
    this.draftBeerFormComponent.saveForm();
    this.onClose();
  }

  editDraftBeer(id: number) {
    this.titleDialog = TituloModalChopeUtil.setTitulo(TituloModalChopeUtil.EDIT.index).header;
    this.display = true;
    this.draftBeerFormComponent.editDraftBeer(id);
  }

  deactivateUser(id: number): void {
    this.productService.delete(id).subscribe(() => this.findAllDraftBeers());
  }

  confirmAction(draftBeer: any): void {
    this.message.confirmarDialog(draftBeer.id,
      () => this.deactivateUser(draftBeer.id),
      EntidadeUtil.CHOPE, draftBeer.descricao);
  }

  onClose(): void {
    this.updateListAfterCreate();
    this.draftBeerFormComponent.formGroup.reset();
  }

  onCloseEntry(): void {
    this.updateListAfterEntry();
    this.entryFormComponent.reset();
  }

  callEntryQtd(): void {
    this.productService.enterProductInput(
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

  private listAllInputs(): void {
    this.drafBeerService.findAll().subscribe((resp) => {
      this.resultRequestList(resp);
    });
  }

  private resultRequestList(result: Page<ChopeListModel[]>): void {
    result.content ? this.draftBeersList = result : this.draftBeersList = [];
  }

  private updateListAfterCreate() {
    if (this.draftBeerFormComponent.list) {
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

}
