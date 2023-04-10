import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {ChopeModel} from "../../../model/chope.model";
import {ChopeService} from "../../../shared/service/chope.service";
import {MensagensChopeUtil} from "../util/messages/mensagens-chope.util";

@Component({
  selector: 'app-chope-prod',
  templateUrl: './chope-prod.component.html',
  styleUrls: ['./chope-prod.component.scss']
})
export class ChopeProdComponent implements OnInit {

  @Input() draftBeer: ChopeModel;
  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;
  newDraftBeer: ChopeModel;

  list: boolean = false;

  constructor(private builder: FormBuilder,
              private draftBeerService: ChopeService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      etiquetaRFID: [null, [Validators.required]],
      qtdeEstoque: [null, [Validators.required]],
      precoCompra: [null, [Validators.required]],
      descricao: [null, [Validators.required]],
      unidade: [null, [Validators.required]],
      pontoEncomenda: [null, [Validators.required]],
      precoVenda: [null, [Validators.required]],
    });
  }

  saveForm(): void {
    this.newDraftBeer = this.formGroup.getRawValue();
    this.draftBeerService.save(this.newDraftBeer)
      .subscribe({
        next: () => {
          this.showSuccessMsgAccordingToId(this.newDraftBeer.id);
          this.closeForm();
          this.list = true;
        },
        error: (error) => {
          this.showErrorMsgAccordingToId(this.newDraftBeer.id, error.message);
        }
      });
  }

  editDraftBeer(id: number): void {
    this.draftBeerService.findById(id).subscribe({
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

  private showSuccessMsgAccordingToId(idDraftBeer: number): void {
    idDraftBeer ? this.message.showSuccess(MensagensChopeUtil.UPDATE_SUCCESSFUL_DRAFT_BEER)
      : this.message.showSuccess(MensagensChopeUtil.SUCCESS_CREATED_DRAFT_BEER);
  }

  private showErrorMsgAccordingToId(idDraftBeer: number, errorMsg: string): void {
    idDraftBeer ? this.message.showError(MensagensChopeUtil.ERROR_UPDATE, errorMsg)
      : this.message.showError(MensagensChopeUtil.ERROR_CREATED, errorMsg);
  }

}
