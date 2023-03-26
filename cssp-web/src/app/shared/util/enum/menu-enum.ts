export class MenuEnum {
  static readonly USUARIO = new MenuEnum(0, 'Usuarios');
  static readonly RELATORIO = new MenuEnum(1, 'Relatorios');
  static readonly PRODUTO = new MenuEnum(2, 'Produtos');
  static readonly CLIENTE = new MenuEnum(3, 'Clientes');
  static readonly CAIXA = new MenuEnum(4, 'Caixa');
  static readonly COZINHA = new MenuEnum(5, 'Cozinha');
  static readonly SELF_SERVICE = new MenuEnum(6, 'Self-Service');
  static readonly CHOPE = new MenuEnum(7, 'Chope');

  static values = [
    MenuEnum.USUARIO,
    MenuEnum.RELATORIO,
    MenuEnum.PRODUTO,
    MenuEnum.CLIENTE,
    MenuEnum.CAIXA,
    MenuEnum.COZINHA,
    MenuEnum.SELF_SERVICE,
    MenuEnum.CHOPE,
  ];

  constructor(
    public index: number,
    public titulo: string
  ) {
  }

  static obterPorIndex(index: number): MenuEnum | any {
    return MenuEnum.values.find(menu => menu.index === index);
  }

  static setClasse(id: number): MenuEnum {
    return MenuEnum.obterPorIndex(id);
  }
}
