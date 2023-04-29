import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ColumnUtil} from "../../../shared/util/columnUtil";
import {UsuarioListModel} from "../../../model/list/usuario-list.model";
import {UsuarioModel} from "../../../model/usuario.model";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {UsuarioFormComponent} from "../usuario-form/usuario-form.component";
import {UsuarioService} from "../../../shared/service/usuario.service";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {TituloModalUsuarioUtil} from "../util/titulo-modal-usuario.util";
import {EntidadeUtil} from "../../../shared/util/entidade.util";
import {finalize} from "rxjs";
import {Page} from "../../../shared/util/page";
import {UsuarioColumnUtil} from "../util/usuario-column.util";
import {MensagensUsuarioUtil} from "../util/mensagens-usuario.util";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.scss']
})
export class UsuarioListComponent implements OnInit {

  columns: ColumnUtil[] = UsuarioColumnUtil.USER_COLUMNS;
  usersList: Page<UsuarioListModel[]> | any = new Page<UsuarioListModel[]>();

  user: UsuarioModel;

  titleDialog: string

  @BlockUI() blockUI: NgBlockUI;
  @Input() display = false;
  @ViewChild(UsuarioFormComponent) userFormComponent: UsuarioFormComponent;

  constructor(private userService: UsuarioService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.blockUI.start();
    this.userService.findAll()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          this.message.showInfo(MensagensUsuarioUtil.ERROS_LIST_ALL, MensagensProntasUtil.ERROR);
        }
      });
  }

  listAllUsers(): void {
    this.userService.findAll().subscribe((resp) => {
      this.resultRequestList(resp)
    });
  }

  private resultRequestList(result: Page<UsuarioListModel[]>): void {
    result.content ? this.usersList = result : this.usersList = [];
  }

  newUser(): void {
    this.titleDialog = TituloModalUsuarioUtil.setTitulo(TituloModalUsuarioUtil.NEW.index).header;
    this.userFormComponent.formGroup.reset();
    this.display = true;
  }

  onSave(): void {
    this.userFormComponent.saveUserForm();
    this.onClose();
  }

  editUser(id: number): void {
    this.titleDialog = TituloModalUsuarioUtil.setTitulo(TituloModalUsuarioUtil.EDIT.index).header;
    this.display = true;
    this.userFormComponent.editUser(id);
  }

  deactivateUser(id: number): void {
    this.userService.delete(id).subscribe(() => this.listAllUsers());
  }

  confirmAction(info: any): void {
    this.message.confirmarDialog(info.id, () => this.deactivateUser(info.id), EntidadeUtil.USUARIO, info.nome)
  }

  resetForm(): void {
    this.userFormComponent.closeForm();
  }

  onClose(): void {
    this.updateList();
    this.userFormComponent.formGroup.reset();
  }

  private updateList() {
    if (this.userFormComponent.list) {
      this.listAllUsers();
    }
    this.display = false;
  }

}
