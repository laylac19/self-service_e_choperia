import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Page} from "../util/page";
import {InsumoListModel} from "../../model/list/insumo-list.model";
import {ProdutoModel} from "../../model/produto.model";

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private http: HttpClient) { }

  resourceUrl = environment.apiUrl + '/produto';

  listAllInputs(): Observable<Page<InsumoListModel[]>> {
    return this.http.get<Page<InsumoListModel[]>>(this.resourceUrl + '/listar-insumos');
  }

  findById(id: number): Observable<ProdutoModel> {
    return this.http.get<ProdutoModel>(this.resourceUrl + '/' + id);
  }

  findInputByBarCode(barCode: string): Observable<InsumoListModel> {
    return this.http.get<InsumoListModel>(this.resourceUrl + '/buscar-cod-barras/' + barCode);
  }

  save(entity: ProdutoModel): Observable<ProdutoModel> {
    return this.http.post<ProdutoModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<ProdutoModel> {
    return this.http.delete<ProdutoModel>(this.resourceUrl + '/' + id);
  }

  enterProductInput(inputs: { id: number, qtdeEstoque: number }[]): Observable<ProdutoModel[]> {
    return this.http.put<any>(this.resourceUrl + '/entrada-produto', inputs);
  }

  releaseProductOutput(inputs: { id: number, qtdeEstoque: number }[]): Observable<ProdutoModel[]> {
    return this.http.put<any>(this.resourceUrl + '/saida-produto', inputs);
  }

  findSelfServiceProduct(): Observable<ProdutoModel> {
    return this.http.get<ProdutoModel>(this.resourceUrl + '/buscar-self-service');
  }

  getWeightValue(): Observable<any> {
    return this.http.get("http://192.168.229.20/peso");
  }

}
