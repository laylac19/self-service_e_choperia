import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RelatorioEntreDatasModel} from "../../../model/report/relatorio-entre-datas.model";

@Component({
  selector: 'app-modal-datas-relatorio',
  templateUrl: './modal-datas-relatorio.component.html',
  styleUrls: ['./modal-datas-relatorio.component.scss']
})
export class ModalDatasRelatorioComponent implements OnInit {
  formGroup: FormGroup;
  dataInicial: Date = new Date();
  dataFinal: Date = new Date();

  report: RelatorioEntreDatasModel;

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();
  message: boolean;

  constructor(private builder: FormBuilder) {
  }

  ngOnInit(): void {
    this.newForm();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      dataInicial: [null, [Validators.required]],
      dataFinal: [null, [Validators.required]],
      mensagem: [null]
    });
  }

  getFormSendEmail(): any {
    return this.report = this.formGroup.getRawValue();
  }

  getFormDates(): any {
    return this.report = this.formGroup.getRawValue();
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
  }

}
