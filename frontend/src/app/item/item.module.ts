import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CadastroItemComponent } from './cadastro-item/cadastro-item.component';
import { SharedModule } from './../shared/shared.module';
import { ItemRoutingModule } from './item-routing.module';
import { ListagemItensComponent } from './listagem-itens/listagem-itens.component';
import { AlterarItensComponent } from './alterar-itens/alterar-itens.component';



@NgModule({
  declarations: [
    ListagemItensComponent,
    CadastroItemComponent,
    AlterarItensComponent
  ],
  imports: [
    CommonModule,
    ItemRoutingModule,
    SharedModule,
    ReactiveFormsModule,
  ]
})
export class ItemModule { }
