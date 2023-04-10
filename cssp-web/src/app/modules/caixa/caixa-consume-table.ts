import {ColumnUtil} from "../../shared/util/columnUtil";
export class CaixaConsumeTable {

  static CONSUME_TABLE: ColumnUtil[] = [
    {
      header: 'Produto Consumido',
      field: 'descricao',
    },
    {
      header: 'Valor Compra',
      field: 'valor',
    }
  ];
}
