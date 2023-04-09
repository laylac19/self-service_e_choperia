export class TituloModalInsumoUtil {
    static readonly NEW = new TituloModalInsumoUtil(0, 'Novo Insumo');
    static readonly VIEW = new TituloModalInsumoUtil(1, 'Visualizar Dados Insumo');
    static readonly EDIT = new TituloModalInsumoUtil(2, 'Editar Insumo');
    static readonly DELETE = new TituloModalInsumoUtil(3, 'Excluir Insumo');
    static readonly ENTRY = new TituloModalInsumoUtil(4, 'Entrada de Insumo');
    static readonly WITHDRAW = new TituloModalInsumoUtil(5, 'Saida de Insumo');

    static values = [
      TituloModalInsumoUtil.NEW,
      TituloModalInsumoUtil.VIEW,
      TituloModalInsumoUtil.EDIT,
      TituloModalInsumoUtil.DELETE,
      TituloModalInsumoUtil.ENTRY,
      TituloModalInsumoUtil.WITHDRAW,
    ];

    private constructor(
        public index: number,
        public header: string,
    ) {
    }

    static obterPorIndex(index: number): TituloModalInsumoUtil | any {
        return TituloModalInsumoUtil.values.find(titulo => titulo.index === index);
    }

    static setTitulo(id: number): TituloModalInsumoUtil {
        return TituloModalInsumoUtil.obterPorIndex(id);
    }
}
