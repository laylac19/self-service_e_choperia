import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {FormBuilder} from "@angular/forms";
import {RelatorioEntreDatasModel} from "../../../model/report/relatorio-entre-datas.model";
import {ClienteCompraService} from "../../../shared/service/cliente-compra.service";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {ModalDatasRelatorioComponent} from "../modal-datas-relatorio/modal-datas-relatorio.component";
import {RelatorioService} from "../../../shared/service/relatorio.service";

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.scss']
})
export class RelatorioComponent implements OnInit {

  report: RelatorioEntreDatasModel;

  @BlockUI() blockUI: NgBlockUI;

  @Input() displayCustomerPurchases = false;
  @Input() displayModalPopularBeer = false;
  @Input() displayDialogEmail = false;

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();
  @ViewChild(ModalDatasRelatorioComponent) datesReportComponent: ModalDatasRelatorioComponent;


  constructor(private builder: FormBuilder,
              private reportService: RelatorioService,
              private clientPurchaseService: ClienteCompraService,
              private message: MensagensConfirmacao) {
  }


  ngOnInit(): void {
  }

  openModalPopularBeer() {
    this.datesReportComponent.formGroup.reset();
    this.datesReportComponent.message = false;
    this.displayModalPopularBeer = true;
  }

  openModalCustomerPurchases() {
    this.datesReportComponent.formGroup.reset();
    this.datesReportComponent.message = false;
    this.displayCustomerPurchases = true;
  }

  openRequestToSendEmail() {
    this.datesReportComponent.formGroup.reset();
    this.datesReportComponent.message = true;
    this.displayDialogEmail = true;
  }

  generateBalanceReportProductInStock(): void {
    this.reportService.balanceReportProductInStock()
      .subscribe({
        next: (response: any) => {
          this.displayModalPopularBeer = false;
          const blob = new Blob([response], {type: 'application/pdf'});
          const url = URL.createObjectURL(blob);
          window.open(url);
          this.message.showSuccess(MensagensProntasUtil.SUCCESSFULLY_GENERATED_REPORT);
        },
        error: (error) => {
          this.message.showError('Erro', error.mensagem);
        }
      });
  }

  generatePointOfOrderProductReport(): void {
    this.reportService.pointOfOrderProductReport()
      .subscribe({
        next: (response: any) => {
          const blob = new Blob([response], {type: 'application/pdf'});
          const url = URL.createObjectURL(blob);
          window.open(url);
          this.message.showSuccess(MensagensProntasUtil.SUCCESSFULLY_GENERATED_REPORT);
        },
        error: (error) => {
          console.log(error);
          this.message.showError(MensagensProntasUtil.ERROR, error.mensagem);
        }
      });
  }

  generateReportMostConsumedBeersInAPeriod(): void {
    this.report = this.datesReportComponent.getFormDates();
    this.validateDatesOfRefport(this.report);
    this.reportService.reportMostConsumedBeersInAPeriod(this.report)
      .subscribe({
        next: (response: any) => {
          this.displayModalPopularBeer = false;
          const blob = new Blob([response], {type: 'application/pdf'});
          const url = URL.createObjectURL(blob);
          window.open(url);
        },
        error: (error) => {
          console.log(error)
          this.message.showError(MensagensProntasUtil.ERROR, error.mensagem)
        }
      });
  }

  sendEmail() {
    this.report = this.datesReportComponent.getFormSendEmail();
    this.clientPurchaseService.sendEmail(this.report)
      .subscribe({
        next: () => {
          this.message.showSuccess('E-mail(s) enviado(s) com sucesso!');
          this.datesReportComponent.closeForm();
          this.onClose();
        },
        error: (error) => {
          this.message.showError(MensagensProntasUtil.ERROR, MensagensProntasUtil.SUB_MESSAGE_ERROR)
        }
      });
  }

  generateCustomerReportWithAmountPurchasedInPeriod() {
    this.report = this.datesReportComponent.getFormDates();
    this.validateDatesOfRefport(this.report);
    this.reportService.customerReportWithAmountPurchasedInPeriod(this.report)
      .subscribe({
        next: (response: any) => {
          const blob = new Blob([response], {type: 'application/pdf'});
          const url = URL.createObjectURL(blob);
          window.open(url);
          this.message.showSuccess(MensagensProntasUtil.SUCCESSFULLY_GENERATED_REPORT);
          this.datesReportComponent.closeForm();
          this.onClose();
        },
        error: (error) => {
          console.log(error);
          this.message.showError(MensagensProntasUtil.ERROR, error.mensagem)
        }
      });
  }

  onClose(): void {
    this.datesReportComponent.formGroup.reset();
    this.displayDialogEmail = false;
    this.displayCustomerPurchases = false;
    this.displayModalPopularBeer = false;
  }

  private validateDatesOfRefport(report: RelatorioEntreDatasModel) {
    if (this.report.dataInicial && this.report.dataFinal && this.report.dataFinal < this.report.dataInicial) {
      this.message.showError('Datas Inválidas', 'A data inicial não pode ser menor do que a final');
      return;
    }
  }

}
