import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RelatorioEntreDatasModel} from "../../../model/report/relatorio-entre-datas.model";
import {FuncoesUtil} from "../../../shared/util/funcoes.util";

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
      'dataInicial': [null, [Validators.required]],
      'dataFinal': [null, [Validators.required]],
      'mensagem': [null]
    });
  }

  getFormSendEmail(): any {
    return this.report = this.formGroup.getRawValue();
  }

  getFormDates(): any {
    console.log(1)
    console.log(this.report)
    console.log(this.formGroup)
    this.report = this.formGroup.getRawValue()
    console.log(2)
    console.log(this.report.dataInicial)
    console.log(this.report.dataFinal)
    this.report.dataInicial = FuncoesUtil.convertToDate(this.report.dataInicial);
    this.report.dataFinal = FuncoesUtil.convertToDate(this.report.dataFinal);
    console.log(3)
    console.log(this.report)
    console.log('_____________________________________________________')
    return this.report;
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
    console.log('LIMPA TUDO')
  }

}
