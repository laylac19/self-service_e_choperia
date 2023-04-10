import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {ChopeCompraModel} from "../../model/chope-compra.model";

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/chope-compra';

  findById(id: number): Observable<ChopeCompraModel> {
    return this.http.get<ChopeCompraModel>(this.resourceUrl + '/' + id);
  }

  save(entity: ChopeCompraModel): Observable<ChopeCompraModel> {
    return this.http.post<ChopeCompraModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(this.resourceUrl + '/' + id);
  }

}
