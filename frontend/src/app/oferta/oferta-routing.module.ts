import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CadastrarOfertaComponent } from './cadastrar-oferta/cadastrar-oferta.component';

const routes: Routes = [
  { path: '', component: CadastrarOfertaComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OfertaRoutingModule { }
