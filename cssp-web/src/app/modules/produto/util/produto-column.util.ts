import {ColumnUtil} from "../../../shared/util/columnUtil";

export class ProdutoColumnUtil {

	static INPUT_COLUMNS: ColumnUtil[] = [
		{
			header: 'Código de Barras',
			field: 'codigoBarras'
		},
		{
			header: 'Descrição',
			field: 'descricao',
		},
		{
			header: 'Qtde. Estoque',
			field: 'qtdeEstoque',
		},
		{
			header: 'Preço Compra',
			field: 'precoCompra',
      type: 'price',
      pipe: 'currency',
		},
    {
      header: 'Unidade',
      field: 'unidade'
    },
    {
			header: 'Ponto de Encomenda',
			field: 'pontoEncomenda'
		},
    {
			header: 'Preço de Venda',
			field: 'precoVenda',
      type: 'price',
      pipe: 'currency',
		},
    {
			header: 'Ações',
			field: 'acoes',
      columnWidth: '132px'
		},
	];

  static SELF_SERVICE_COLUMNS: ColumnUtil[] = [
		{
			header: 'ID',
			field: 'id'
		},
		{
			header: 'Descrição',
			field: 'descricao',
		},
		{
			header: 'Preço Kg',
			field: 'precoKg',
      type: 'price',
      pipe: 'currency',
		},
    {
			header: 'Ações',
			field: 'acoes',
      columnWidth: '132px'
		},
	];

  static DRAFT_BEER_COLUMNS: ColumnUtil[] = [
		{
			header: 'RFID',
			field: 'etiquetaRFID'
		},
		{
			header: 'Descrição',
			field: 'descricao',
		},
		{
			header: 'Qtde. Estoque',
			field: 'qtdeEstoque',
		},
		{
			header: 'Preço Compra',
			field: 'precoCompra',
      type: 'price',
      pipe: 'currency',
		},
    {
      header: 'Unidade',
      field: 'unidade'
    },
    {
			header: 'Ponto de Encomenda',
			field: 'pontoEncomenda'
		},
    {
			header: 'Preço de Venda',
			field: 'precoVenda',
      type: 'price',
      pipe: 'currency',
		},
    {
			header: 'Ações',
			field: 'acoes',
      columnWidth: '132px'
		},
	];

  static ENTRY_PRODUCTS_COLUMNS: ColumnUtil[] = [
    {
      header: 'Código de Barras',
      field: 'codigoBarras'
    },
    {
      header: 'Descrição',
      field: 'descricao',
    },
    {
      header: 'Qtde. Estoque',
      field: 'qtdeEstoque',
    }
  ];

  static WITHDRAW_PRODUCTS_COLUMNS: ColumnUtil[] = [
    {
      header: 'Código de Barras',
      field: 'codigoBarras'
    },
    {
      header: 'Descrição',
      field: 'descricao',
    },
    {
      header: 'Qtde. Estoque',
      field: 'qtdeEstoque',
    }
  ];
}
