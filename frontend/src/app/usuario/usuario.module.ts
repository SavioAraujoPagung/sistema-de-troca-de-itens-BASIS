import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { CadastroPageComponent } from './cadastro-page/cadastro-page.component';
import { SharedModule } from './../shared/shared.module';
import { UsuarioRoutingModule } from './usuario-routing.module';
import { ListagemPageComponent } from './listagem-page/listagem-page.component'



@NgModule({
  declarations: [
    ListagemPageComponent,
    CadastroPageComponent
  ],
  imports: [
    CommonModule,
    UsuarioRoutingModule,
    SharedModule,
    ReactiveFormsModule,
  ]
})
export class UsuarioModule { }
