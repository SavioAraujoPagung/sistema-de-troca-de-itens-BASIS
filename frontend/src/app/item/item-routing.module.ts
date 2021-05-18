import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListagemItensComponent } from './listagem-itens/listagem-itens.component';

const routes: Routes = [
  { path: '', component: ListagemItensComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItemRoutingModule { }
