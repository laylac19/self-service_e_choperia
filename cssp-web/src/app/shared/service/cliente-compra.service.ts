import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
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

}
