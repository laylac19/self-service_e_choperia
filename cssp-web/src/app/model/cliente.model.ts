import {ClienteCompraModelList} from "./list/cliente-compra-list.model";

export interface ClienteModel {
  id: number;
  numCartaoRFID: string;
  nome: string;
  telefone: string;
  email: string;
  cpf: string;
  compras?: ClienteCompraModelList[];
}
