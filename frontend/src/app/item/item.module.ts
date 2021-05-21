import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from './../shared/shared.module';

import { CadastroItemComponent } from './cadastro-item/cadastro-item.component';
import { ItemRoutingModule } from './item-routing.module';
import { ListagemItensComponent } from './listagem-itens/listagem-itens.component';
import { FichaItemComponent } from './ficha-item/ficha-item.component';



@NgModule({
  declarations: [
    ListagemItensComponent,
    CadastroItemComponent,
    FichaItemComponent
  ],
  imports: [
    CommonModule,
    ItemRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ],
  exports: [
    FichaItemComponent
  ]
})
export class ItemModule { }
