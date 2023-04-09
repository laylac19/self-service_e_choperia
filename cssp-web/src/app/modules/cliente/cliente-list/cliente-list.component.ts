import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {ClienteFormComponent} from "../cliente-form/cliente-form.component";
import {ClienteService} from "../../../shared/service/cliente.service";
import {MensagensUsuarioUtil} from "../../usuario/util/mensagens-usuario.util";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {TituloModalUsuarioUtil} from "../../usuario/util/titulo-modal-usuario.util";
import {EntidadeUtil} from "../../../shared/util/entidade.util";
import {ClienteListModel} from "../../../model/list/cliente-list.model";
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {ClienteColumnUtil} from "../util/cliente-column.util";
import {Page} from "../../../shared/util/page";
import {finalize} from "rxjs";

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.scss']
})
export class ClienteListComponent implements OnInit {

  columns: ColumnUtil[] = ClienteColumnUtil.CUSTOMER_COLUMNS;
  customersList: Page<ClienteListModel[]> | any = new Page<ClienteListModel[]>();
  customerEntries: ClienteListModel[] = [];

  titleDialog: string;

  @BlockUI() blockUI: NgBlockUI;
  @Input() display = false;
  @ViewChild(ClienteFormComponent) customerFormComponent: ClienteFormComponent;

  constructor(private customerService: ClienteService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.listAllCustomers();
    this.listCustomersWhoHaveEntered();
  }

  listAllCustomers(): void {
    this.blockUI.start();
    this.customerService.findAll()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          this.message.showInfo(MensagensUsuarioUtil.ERROS_LIST_ALL, MensagensProntasUtil.ERROR);
        }
      })
  }

  listCustomersWhoHaveEntered(): void {
    this.customerService.listCustomersWhoHaveEntered()
      .subscribe(
        (result) => {
          this.resultRequestEntriesList(result);
        });
  }

  private resultRequestList(result: Page<ClienteListModel[]>): void {
    result.content ? this.customersList = result : this.customersList = [];
  }

  private resultRequestEntriesList(result: ClienteListModel[]): void {
    result ? this.customerEntries = result : this.customerEntries = [];
  }

  newCustomer(): void {
    this.titleDialog = TituloModalUsuarioUtil.setTitulo(TituloModalUsuarioUtil.NEW.index).header;
    this.customerFormComponent.formGroup.reset();
    this.display = true;
  }

  onSave(): void {
    this.customerFormComponent.saveForm();
    this.listAllCustomers();
    this.onClose();
  }

  editCustomer(id: number): void {
    this.titleDialog = TituloModalUsuarioUtil.setTitulo(TituloModalUsuarioUtil.EDIT.index).header;
    this.display = true;
    this.customerFormComponent.editCustomer(id);
  }

  deactivateCustomer(id: number): void {
    this.customerService.delete(id).subscribe(() => this.listAllCustomers());
  }

  confirmAction(customer: any): void {
    this.message.confirmarDialog(customer.id, () => this.deactivateCustomer(customer.id), EntidadeUtil.CLIENTE, customer.nome)
  }

  onClose(): void {
    this.display = false;
    this.customerFormComponent.formGroup.reset();
  }

  checkIn() {

  }

  checkOut() {

  }

}
