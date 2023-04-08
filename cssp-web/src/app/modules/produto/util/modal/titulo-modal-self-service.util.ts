export class TituloModalSelfServiceUtil {
    static readonly NEW = new TituloModalSelfServiceUtil(0, 'Novo Prato');
    static readonly VIEW = new TituloModalSelfServiceUtil(1, 'Visualizar Dados Prato');
    static readonly EDIT = new TituloModalSelfServiceUtil(2, 'Editar Prato');
    static readonly DELETE = new TituloModalSelfServiceUtil(3, 'Excluir Prato');

    static values = [
      TituloModalSelfServiceUtil.NEW,
      TituloModalSelfServiceUtil.VIEW,
      TituloModalSelfServiceUtil.EDIT,
      TituloModalSelfServiceUtil.DELETE,
    ];

    private constructor(
        public index: number,
        public header: string,
    ) {
    }

    static obterPorIndex(index: number): TituloModalSelfServiceUtil | any {
        return TituloModalSelfServiceUtil.values.find(titulo => titulo.index === index);
    }

    static setTitulo(id: number): TituloModalSelfServiceUtil {
        return TituloModalSelfServiceUtil.obterPorIndex(id);
    }
}
