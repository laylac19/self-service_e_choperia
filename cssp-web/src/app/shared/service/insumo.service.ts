import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Page} from "../util/page";
import {InsumoListModel} from "../../model/list/insumo-list.model";
import {InsumoModel} from "../../model/insumo.model";

@Injectable({
  providedIn: 'root'
})
export class InsumoService {

  constructor(private http: HttpClient) { }

  resourceUrl = environment.apiUrl + '/insumo';

  findAll(): Observable<Page<InsumoListModel[]>> {
    return this.http.get<Page<InsumoListModel[]>>(this.resourceUrl);
  }

  findById(id: number): Observable<InsumoModel> {
    return this.http.get<InsumoModel>(this.resourceUrl + '/' + id);
  }

  findByBarCode(barCode: string): Observable<InsumoModel> {
    return this.http.get<InsumoModel>(this.resourceUrl + '/buscar-cod-barras/' + barCode);
  }

  insert(entity: InsumoModel): Observable<InsumoModel> {
    return this.http.post<InsumoModel>(this.resourceUrl, entity);
  }

  update(entity: InsumoModel): Observable<InsumoModel> {
    return this.http.put<InsumoModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<InsumoModel> {
    return this.http.delete<InsumoModel>(this.resourceUrl + '/' + id);
  }

  enterProductInput(inputs: { id: number, qtdeEstoque: number }[]): Observable<InsumoModel[]> {
    return this.http.put<any>(this.resourceUrl + '/entrada-produto', inputs);
  }

  releaseProductOutput(inputs: { id: number, qtdeEstoque: number }[]): Observable<InsumoModel[]> {
    return this.http.put<any>(this.resourceUrl + '/saida-produto', inputs);
  }

}
