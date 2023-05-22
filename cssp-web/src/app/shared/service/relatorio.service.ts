import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {ProdutoEstoqueReportModel} from "../../model/report/produto-estoque-report.model";
import {ProdutoPontoEncomendaReportModel} from "../../model/report/produto-ponto-encomenda-report.model";

@Injectable({
  providedIn: 'root'
})
export class RelatorioService {

  constructor(private http: HttpClient) { }

  resourceUrl = environment.apiUrl + '/relatorio';

  balanceReportProductInStock(): Observable<ProdutoEstoqueReportModel[]> {
    return this.http.get<ProdutoEstoqueReportModel[]>(this.resourceUrl + '/relatorio-estoque-produtos');
  }

  pointOfOrderProductReport(): Observable<ProdutoPontoEncomendaReportModel[]> {
    return this.http.get<ProdutoPontoEncomendaReportModel[]>(this.resourceUrl + '/relatorio-ponto-encomenda-produtos');
  }

  // reportMostConsumedBeersInAPeriod(report: RelatorioEntreDatasModel): Observable<ChopesPopularesReportModel[]> {
  //   return this.http.get<ChopesPopularesReportModel[]>($`{this.resourceUrl/relatorio-chopes-populare}`);
  // }
  //
  // customerReportWithAmountPurchasedInPeriod(report: RelatorioEntreDatasModel): Observable<TotalComprasClientesReportModel[]> {
  //   return this.http.get<TotalComprasClientesReportModel[]>(this.resourceUrl + '/relatorio-total-compras-clientes', report);
  // }

}
