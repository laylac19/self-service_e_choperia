import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: '', loadChildren: () => import('./modules/painel-adm/painel-adm.module').then(m => m.PainelAdmModule)},
  {path: 'Usuarios', loadChildren: () => import('./modules/usuario/usuario.module').then(m => m.UsuarioModule)},
  {path: 'Relatorio', loadChildren: () => import('./modules/relatorio/relatorio.module').then(m => m.RelatorioModule)},
  {path: 'Produtos', loadChildren: () => import('./modules/produto/produto.module').then(m => m.ProdutoModule)},
  {path: 'Clientes', loadChildren: () => import('./modules/cliente/cliente.module').then(m => m.ClienteModule)},
  {path: 'Caixa', loadChildren: () => import('./modules/caixa/caixa.module').then(m => m.CaixaModule)},
  {path: 'Cozinha', loadChildren: () => import('./modules/cozinha/cozinha.module').then(m => m.CozinhaModule)},
  {path: 'Self-Service', loadChildren: () => import('./modules/self-service/self-service.module').then(m => m.SelfServiceModule)},
  {path: 'Chope', loadChildren: () => import('./modules/chope/chope.module').then(m => m.ChopeModule)},
  {path: 'Painel', loadChildren: () => import('./modules/painel-ssc/painel-ssc.module').then(m => m.PainelSscModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
