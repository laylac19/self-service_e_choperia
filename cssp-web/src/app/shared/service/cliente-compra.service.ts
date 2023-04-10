import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {ClienteCompraModel} from "../../model/cliente-compra.model";
import {ClienteCompraModelList} from "../../model/list/cliente-compra-list.model";
import {ClienteCaixaList} from "../../model/list/cliente-caixa-list";

@Injectable({
  providedIn: 'root'
})
export class ClienteCompraService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/cliente-compra';

  listPurchasedItemsOfCustomer(cardRFID: string): Observable<ClienteCaixaList[]> {
    return this.http.get<ClienteCaixaList[]>(this.resourceUrl + '/compras-cliente/' + cardRFID);
  }

  findById(id: number): Observable<ClienteCompraModel> {
    return this.http.get<ClienteCompraModel>(this.resourceUrl + '/' + id);
  }

  insert(entity: ClienteCompraModel): Observable<ClienteCompraModel> {
    return this.http.post<ClienteCompraModel>(this.resourceUrl, entity);
  }

  update(entity: ClienteCompraModel): Observable<ClienteCompraModel> {
    return this.http.put<ClienteCompraModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<ClienteCompraModel> {
    return this.http.delete<ClienteCompraModel>(this.resourceUrl + '/' + id);
  }

  findByRFID(cartaoRFID: string): Observable<ClienteCompraModelList[]> {
    return this.http.get<ClienteCompraModelList[]>(this.resourceUrl + '/compras-cliente/' + cartaoRFID);
  }


}
