import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {SelfServicePratoModel} from "../../../model/self-service-prato.model";
import {SelfServiceService} from "../../../shared/service/self-service.service";
import {MensagensSelfServiceUtil} from "../util/messages/mensagens-self-service.util";

@Component({
  selector: 'app-self-service-prod',
  templateUrl: './self-service-prod.component.html',
  styleUrls: ['./self-service-prod.component.scss']
})
export class SelfServiceProdComponent implements OnInit {

  @Input() dishModel: SelfServicePratoModel;
  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;
  newDish: SelfServicePratoModel;

  list: boolean = false;

  constructor(private builder: FormBuilder,
              private dishService: SelfServiceService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      descricao: [null, [Validators.required]],
      precokg: [null, [Validators.required]],
    });
  }

  saveForm(): void {
    this.newDish = this.formGroup.getRawValue();
    this.dishService.insert(this.newDish)
      .subscribe({
        next: () => {
          this.showSuccessMsgAccordingToId(this.newDish.id);
          this.closeForm();
          this.list = true;
        },
        error: (error) => {
          this.showErrorMsgAccordingToId(this.newDish.id, error.message);
        }
      });
  }

  editForm(id: number): void {
    this.dishService.findById(id).subscribe({
        next: (response) => {
          this.formGroup.patchValue(response);
        },
      }
    );
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
  }

  private showSuccessMsgAccordingToId(idDish: number): void {
    idDish ? this.message.showSuccess(MensagensSelfServiceUtil.UPDATE_SUCCESSFUL_DISH)
      : this.message.showSuccess(MensagensSelfServiceUtil.SUCCESS_CREATED_DISH);
  }

  private showErrorMsgAccordingToId(idDish: number, errorMsg: string): void {
    idDish ? this.message.showError(MensagensSelfServiceUtil.ERROR_UPDATE, errorMsg)
      : this.message.showError(MensagensSelfServiceUtil.ERROR_CREATED, errorMsg);
  }

}
