export class TituloModalChopeUtil {
    static readonly NEW = new TituloModalChopeUtil(0, 'Novo Chope');
    static readonly VIEW = new TituloModalChopeUtil(1, 'Visualizar Dados Chope');
    static readonly EDIT = new TituloModalChopeUtil(2, 'Editar Chope');
    static readonly DELETE = new TituloModalChopeUtil(3, 'Excluir Chope');
  static readonly ENTRY = new TituloModalChopeUtil(4, 'Entrada de Insumo');

    static values = [
      TituloModalChopeUtil.NEW,
      TituloModalChopeUtil.VIEW,
      TituloModalChopeUtil.EDIT,
      TituloModalChopeUtil.DELETE,
      TituloModalChopeUtil.ENTRY
    ];

    private constructor(
        public index: number,
        public header: string,
    ) {
    }

    static obterPorIndex(index: number): TituloModalChopeUtil | any {
        return TituloModalChopeUtil.values.find(titulo => titulo.index === index);
    }

    static setTitulo(id: number): TituloModalChopeUtil {
        return TituloModalChopeUtil.obterPorIndex(id);
    }
}
