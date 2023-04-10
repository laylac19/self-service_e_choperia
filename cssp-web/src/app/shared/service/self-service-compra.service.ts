import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {SelfServiceCompraModel} from "../../model/self-service-compra.model";

@Injectable({
  providedIn: 'root'
})
export class SelfServiceCompraService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/self-service-compra';

  findById(id: number): Observable<SelfServiceCompraModel> {
    return this.http.get<SelfServiceCompraModel>(this.resourceUrl + '/' + id);
  }

  save(entity: SelfServiceCompraModel): Observable<SelfServiceCompraModel> {
    return this.http.post<SelfServiceCompraModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(this.resourceUrl + '/' + id);
  }

}
