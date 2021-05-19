import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CadastroPageComponent } from './cadastro-page/cadastro-page.component';
import { ListagemPageComponent } from './listagem-page/listagem-page.component';

const routes: Routes = [
  { path: '', component: ListagemPageComponent },
  { path: 'cadastro', component: CadastroPageComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
