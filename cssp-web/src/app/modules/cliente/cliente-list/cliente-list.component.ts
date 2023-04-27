import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {ClienteFormComponent} from "../cliente-form/cliente-form.component";
import {ClienteService} from "../../../shared/service/cliente.service";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {EntidadeUtil} from "../../../shared/util/entidade.util";
import {ClienteListModel} from "../../../model/list/cliente-list.model";
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {ClienteColumnUtil} from "../util/cliente-column.util";
import {Page} from "../../../shared/util/page";
import {finalize} from "rxjs";
import {ClienteEntradaComponent} from "../cliente-entrada/cliente-entrada.component";
import {TituloModalClienteUtil} from "../util/titulo-modal-cliente.util";
import {MensagensClienteUtil} from "../util/mensagens-cliente.util";

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
  @Input() display: boolean = false;
  @Input() displayEntry: boolean = false;
  @Input() displayExit: boolean = false;
  @ViewChild(ClienteFormComponent) customerFormComponent: ClienteFormComponent;
  @ViewChild(ClienteEntradaComponent) customerEntryComponent: ClienteEntradaComponent;

  // @ViewChild(ClienteSaidaComponent) customerExitComponent: ClienteSaidaComponent;

  constructor(private customerService: ClienteService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.findAllCustomers();
    this.findCustomersWhoHaveEntered();
  }

  findAllCustomers(): void {
    this.blockUI.start();
    this.customerService.findAll()
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

  findCustomersWhoHaveEntered(): void {
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
    this.titleDialog = TituloModalClienteUtil.setTitulo(TituloModalClienteUtil.NEW.index).header;
    this.customerFormComponent.formGroup.reset();
    this.display = true;
  }

  onSave(): void {
    this.customerFormComponent.saveForm();
    this.findAllCustomers();
    this.onClose();
  }

  onSaveEntry(): void {
    this.customerEntryComponent.saveForm();
    this.findAllCustomers();
    this.onCloseEntryDialog();
  }

  editCustomer(id: number): void {
    this.titleDialog = TituloModalClienteUtil.setTitulo(TituloModalClienteUtil.EDIT.index).header;
    this.display = true;
    this.customerFormComponent.editCustomer(id);
  }

  deactivateCustomer(id: number): void {
    this.customerService.delete(id).subscribe(() => this.findAllCustomers());
  }

  confirmAction(customer: any): void {
    this.message.confirmarDialog(customer.id, () => this.deactivateCustomer(customer.id), EntidadeUtil.CLIENTE, customer.nome)
  }

  onClose(): void {
    this.display = false;
    this.customerFormComponent.formGroup.reset();
  }

  onCloseEntryDialog(): void {
    this.displayEntry = false;
    this.customerEntryComponent.formGroup.reset();
  }

  checkIn(id: number) {
    this.titleDialog = TituloModalClienteUtil.setTitulo(TituloModalClienteUtil.ENTRY.index).header;
    this.customerEntryComponent.findCustomerByID(id);
    this.displayEntry = true;
  }

  onCloseReleaseCard(): void {
    this.displayExit = false
  }

  customerExit(id: number): void {
    this.customerService.customerExit(id).subscribe(() => {
      this.message.showSuccess(MensagensClienteUtil.SUCCESS_EXIT);
      this.findAllCustomers();
    });
  }

  confirmActionExit(customer: any): void {
    this.message.confirmExitCustomer(customer.id, () => this.customerExit(customer.id), customer.nome)
  }

}
