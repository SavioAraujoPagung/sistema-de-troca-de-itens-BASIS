import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListagemCatalogoComponent } from './listagem-catalogo/listagem-catalogo.component';

const routes: Routes = [
  { path: '', component: ListagemCatalogoComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CatalogoRoutingModule { }
