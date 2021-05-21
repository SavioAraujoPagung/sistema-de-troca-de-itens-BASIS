import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AlterarItensComponent } from './alterar-itens/alterar-itens.component';
import { ListarInventarioComponent } from './listar-inventario/listar-inventario.component';


const routes: Routes = [
   { path: '', component: ListarInventarioComponent },
   //{ path: 'alterar', component: AlterarItensComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InventarioRoutingModule { }