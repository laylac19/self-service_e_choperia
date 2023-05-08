import {ColumnUtil} from "../../shared/util/columnUtil";
export class ImprimirCodBarrasTable {

  static PRODUCTS_TABLE: ColumnUtil[] = [
    {
      header: 'Produto',
      field: 'descricao',
    },
    {
      header: 'Cod. Barras',
      field: 'codigoBarras',
    }
  ];
}
