import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginSuccessComponent } from '@nuvem/angular-base';

import { AuthGuard } from './guard/auth.guard';
import { LoginComponent } from './login/login.component';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { GuestGuard } from './guard/guest.guard';
import { CadastroPageComponent } from './usuario/cadastro-page/cadastro-page.component';


const routes: Routes = [
    { path: '', redirectTo: 'admin', pathMatch: 'full' },
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: 'login', component: LoginComponent, canActivate: [GuestGuard] },
    { path: 'cadastro', component: CadastroPageComponent, canActivate: [GuestGuard] },
    { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule), canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
