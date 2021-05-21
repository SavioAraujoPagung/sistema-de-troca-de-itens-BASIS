import { AuthGuard } from './../guard/auth.guard';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminComponent } from './admin.component';

const routes: Routes = [
  { path: '', component: AdminComponent, children: [
    { path: 'catalogo', loadChildren: () => import('../catalogo/catalogo.module').then(m => m.CatalogoModule), canLoad: [AuthGuard] },
    { path: 'usuarios', loadChildren: () => import('../usuario/usuario.module').then(m => m.UsuarioModule), canLoad: [AuthGuard] },
    { path: 'itens', loadChildren: () => import('../item/item.module').then(m => m.ItemModule), canLoad: [AuthGuard] },
    { path: 'minhas-ofertas', loadChildren: () => import('../minhas-ofertas/minhas-ofertas.module').then(m => m.MinhasOfertasModule), canLoad: [AuthGuard] }
  ] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
