import { ItemModule } from './../item/item.module';
import { SharedModule } from './../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CatalogoRoutingModule } from './catalogo-routing.module';
import { ListagemCatalogoComponent } from './listagem-catalogo/listagem-catalogo.component';


@NgModule({
  declarations: [ListagemCatalogoComponent],
  imports: [
    CommonModule,
    CatalogoRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    ItemModule
  ]
})
export class CatalogoModule { }
