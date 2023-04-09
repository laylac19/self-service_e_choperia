export class TituloModalClienteUtil {
    static readonly NEW = new TituloModalClienteUtil(0, 'Novo Cliente');
    static readonly VIEW = new TituloModalClienteUtil(1, 'Visualizar Dados Cliente');
    static readonly EDIT = new TituloModalClienteUtil(2, 'Editar Cliente');
    static readonly DELETE = new TituloModalClienteUtil(3, 'Excluir Cliente');
    static readonly ENTRY = new TituloModalClienteUtil(4, 'Registrar Entrada de Cliente');
    static readonly EXIT = new TituloModalClienteUtil(5, 'Registrar Saída de Cliente');

    static values = [
      TituloModalClienteUtil.NEW,
      TituloModalClienteUtil.VIEW,
      TituloModalClienteUtil.EDIT,
      TituloModalClienteUtil.DELETE,
      TituloModalClienteUtil.ENTRY,
      TituloModalClienteUtil.EXIT
    ];

    private constructor(
        public index: number,
        public header: string,
    ) {
    }

    static obterPorIndex(index: number): TituloModalClienteUtil | any {
        return TituloModalClienteUtil.values.find(titulo => titulo.index === index);
    }

    static setTitulo(id: number): TituloModalClienteUtil {
        return TituloModalClienteUtil.obterPorIndex(id);
    }
}
