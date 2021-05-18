import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from './../shared/shared.module';
import { ItemRoutingModule } from './item-routing.module';
import { ListagemItensComponent } from './listagem-itens/listagem-itens.component';


@NgModule({
  declarations: [
    ListagemItensComponent
  ],
  imports: [
    CommonModule,
    ItemRoutingModule,
    SharedModule,
  ]
})
export class ItemModule { }
