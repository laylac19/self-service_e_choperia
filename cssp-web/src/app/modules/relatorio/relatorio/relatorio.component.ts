import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ProdutoService} from "../../../shared/service/produto.service";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {FormBuilder} from "@angular/forms";
import {RelatorioEntreDatasModel} from "../../../model/report/relatorio-entre-datas.model";
import {ClienteCompraService} from "../../../shared/service/cliente-compra.service";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {ModalDatasRelatorioComponent} from "../modal-datas-relatorio/modal-datas-relatorio.component";
import {ClienteService} from "../../../shared/service/cliente.service";

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
              private productService: ProdutoService,
              private clientPurchaseService: ClienteCompraService,
              private clientService: ClienteService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
  }

  openModalPopularBeer() {
    this.datesReportComponent.formGroup.reset();
    this.displayModalPopularBeer = true;
  }

  openModalCustomerPurchases() {
    this.datesReportComponent.formGroup.reset();
    this.displayCustomerPurchases = true;
  }

  openRequestToSendEmail() {
    this.datesReportComponent.formGroup.reset();
    this.displayDialogEmail = true;
  }

  generateBalanceReportProductInStock(): void {
    this.productService.balanceReportProductInStock()
      .subscribe({
        next: () => {
          this.message.showSuccess(MensagensProntasUtil.SUCCESSFULLY_GENERATED_REPORT);
        },
        error: (error) => {
          this.message.showError('Erro', error.mensagem);
        }
      });
  }

  generatePointOfOrderProductReport(): void {
    this.productService.pointOfOrderProductReport()
      .subscribe({
        next: () => {
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
    console.log(this.report);
    this.productService.reportMostConsumedBeersInAPeriod(this.report)
      .subscribe({
        next: () => {
          this.message.showSuccess(MensagensProntasUtil.SUCCESSFULLY_GENERATED_REPORT);
          this.datesReportComponent.closeForm();
          this.onClose();
        },
        error: (error) => {
          console.log(error)
          this.message.showError(MensagensProntasUtil.ERROR, error.mensagem)
        }
      });
  }

  sendEmail() {
    this.report = this.datesReportComponent.getFormSendEmail();
    console.log(this.report);
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
    console.log(this.report);
    this.clientService.customerReportWithAmountPurchasedInPeriod(this.report)
      .subscribe({
        next: () => {
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

}
