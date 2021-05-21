import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminComponent } from './admin.component';

const routes: Routes = [
  { path: '', component: AdminComponent, children: [
    { path: 'usuarios', loadChildren: () => import('../usuario/usuario.module').then(m => m.UsuarioModule) },
    { path: 'itens', loadChildren: () => import('../item/item.module').then(m => m.ItemModule) },
    { path: 'inventario', loadChildren: () => import('../inventario/inventario.module').then(m => m.InventarioModule) }
  ] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
