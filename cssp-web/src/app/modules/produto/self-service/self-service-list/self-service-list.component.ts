import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ColumnUtil} from "../../../../shared/util/columnUtil";
import {ProdutoColumnUtil} from "../../util/produto-column.util";
import {SelfServicePratoListModel} from "../../../../model/list/self-service-prato-list.model";
import {Page} from "../../../../shared/util/page";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {SelfServiceProdComponent} from "../self-service-prod/self-service-prod.component";
import {SelfServiceService} from "../../../../shared/service/self-service.service";
import {MensagensConfirmacao} from "../../../../shared/util/msgConfirmacaoDialog.util";
import {finalize} from "rxjs";
import {MensagensProntasUtil} from "../../../../shared/util/messages/MensagensProntas.util";
import {EntidadeUtil} from "../../../../shared/util/entidade.util";
import {TituloModalSelfServiceUtil} from "../../util/modal/titulo-modal-self-service.util";
import {CozinhaService} from "../../../../shared/service/cozinha.service";
import {MensagensSelfServiceUtil} from "../../util/messages/mensagens-self-service.util";

@Component({
  selector: 'app-self-service-list',
  templateUrl: './self-service-list.component.html',
  styleUrls: ['./self-service-list.component.scss']
})
export class SelfServiceListComponent implements OnInit {

  columns: ColumnUtil[] = ProdutoColumnUtil.SELF_SERVICE_COLUMNS;
  dishesList: Page<SelfServicePratoListModel[]> | any = new Page<SelfServicePratoListModel[]>();

  titleDialog: string

  @BlockUI() blockUI: NgBlockUI;
  @Input() display = false;
  @ViewChild(SelfServiceProdComponent) selfServiceFormComponent: SelfServiceProdComponent;

  constructor(private dishService: SelfServiceService,
              private kitchenservice: CozinhaService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.listAllIDishes();
  }

  listAllIDishes(): void {
    this.blockUI.start();
    this.dishService.findAll()
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

  private resultRequestList(result: Page<SelfServicePratoListModel[]>): void {
    result.content ? this.dishesList = result : this.dishesList = [];
  }

  newDish() {
    this.titleDialog = TituloModalSelfServiceUtil.setTitulo(TituloModalSelfServiceUtil.NEW.index).header;
    this.selfServiceFormComponent.formGroup.reset();
    this.display = true;
  }

  onSave(): void {
    this.selfServiceFormComponent.saveForm();
    this.listAllIDishes();
    this.onClose();
  }

  editDish(id: number): void {
    this.titleDialog = TituloModalSelfServiceUtil.setTitulo(TituloModalSelfServiceUtil.EDIT.index).header;
    this.display = true;
    this.selfServiceFormComponent.editForm(id);
  }

  deactivateDish(id: number): void {
    this.dishService.delete(id).subscribe(() => this.listAllIDishes());
  }

  sendRequestDish(id: number): void {
    this.kitchenservice.needToReplacePlate(id).subscribe(() => {
      this.message.showSuccess(MensagensSelfServiceUtil.SUCCESS_REQUESTING_REPLACEMENT);
      this.listAllIDishes()
    });
  }

  confirmAction(dish: any): void {
    this.message.confirmarDialog(dish.id, () => this.deactivateDish(dish.id), EntidadeUtil.USUARIO, dish.descricao)
  }

  confirmRequest(dish: any): void {
    this.message.confirmRequestDishReplacement(dish.id, () => this.sendRequestDish(dish.id), dish.descricao)
  }

  onClose(): void {
    this.display = false;
    this.selfServiceFormComponent.formGroup.reset();
  }
}



