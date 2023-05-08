import {ClienteCaixaList} from "./list/cliente-caixa-list";

export interface ClienteModel {
  id: number;
  numCartaoRFID: string;
  nome: string;
  telefone: string;
  email: string;
  cpf: string;
  compras: ClienteCaixaList[];
}
