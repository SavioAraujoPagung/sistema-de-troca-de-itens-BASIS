import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListarInventarioComponent } from './listar-inventario/listar-inventario.component';


const routes: Routes = [
   { path: '', component: ListarInventarioComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InventarioRoutingModule { }