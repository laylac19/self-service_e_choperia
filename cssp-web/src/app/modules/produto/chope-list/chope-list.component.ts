import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {ProdutoColumnUtil} from "../util/produto-column.util";
import {Page} from "../../../shared/util/page";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {ChopeListModel} from "../../../model/list/chope-list.model";
import {ChopeProdComponent} from "../chope-prod/chope-prod.component";
import {ChopeService} from "../../../shared/service/chope.service";
import {EntidadeUtil} from "../../../shared/util/entidade.util";
import {finalize} from "rxjs";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {TituloModalChopeUtil} from "../util/modal/titulo-modal-chope.util";

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
  @ViewChild(ChopeProdComponent) draftBeerFormComponent: ChopeProdComponent;

  constructor(private drafBeerService: ChopeService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.listAllDraftBeers();
  }

  listAllDraftBeers(): void {
    this.blockUI.start();
    this.drafBeerService.findAll()
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

  private resultRequestList(result: Page<ChopeListModel[]>): void {
    result.content ? this.draftBeersList = result : this.draftBeersList = [];
  }


  registerDraftBeer() {
    this.titleDialog = TituloModalChopeUtil.setTitulo(TituloModalChopeUtil.NEW.index).header;
    this.draftBeerFormComponent.formGroup.reset();
    this.display = true;
  }

  giveEntryToProduct() {

  }

  outputInProduct() {

  }

  printRFIDtag() {

  }

  onSave() {
    this.draftBeerFormComponent.saveForm();
    this.listAllDraftBeers();
    this.onClose();
  }

  editDraftBeer(id: number) {
    this.titleDialog = TituloModalChopeUtil.setTitulo(TituloModalChopeUtil.EDIT.index).header;
    this.display = true;
    this.draftBeerFormComponent.editDraftBeer(id);
  }

  deactivateUser(id: number): void {
    this.drafBeerService.delete(id).subscribe(() => this.listAllDraftBeers());
  }

  confirmAction(draftBeer: any): void {
    this.message.confirmarDialog(draftBeer.id,
      () => this.deactivateUser(draftBeer.id),
      EntidadeUtil.CHOPE, draftBeer.descricao);
  }

  onClose(): void {
    this.display = false;
    this.draftBeerFormComponent.formGroup.reset();
  }
}
