import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlterarItensComponent } from './alterar-itens/alterar-itens.component';
import { ListarInventarioComponent } from './listar-inventario/listar-inventario.component';
import { InventarioRoutingModule } from './inventario-routing.module';



@NgModule({
  declarations: [
    AlterarItensComponent,
    ListarInventarioComponent
  ],
  imports: [
    CommonModule,
    InventarioRoutingModule,
    SharedModule,

  ]
})
export class InventarioModule { }
