import { SharedModule } from './../shared/shared.module';
import { AlterarItensComponent } from './alterar-itens/alterar-itens.component';
import { ListarInventarioComponent } from './listar-inventario/listar-inventario.component';
import { InventarioRoutingModule } from './inventario-routing.module';
import { ItemModule } from '../item/item.module';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AlterarItensComponent,
    ListarInventarioComponent
  ],
  imports: [
    CommonModule,
    InventarioRoutingModule,
    ReactiveFormsModule,
    SharedModule,
    ItemModule
  ]
})
export class InventarioModule { }
