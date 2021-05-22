import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from './../shared/shared.module';

import { CatalogoRoutingModule } from './catalogo-routing.module';
import { ItemModule } from './../item/item.module';
import { ListagemCatalogoComponent } from './listagem-catalogo/listagem-catalogo.component';
import { CriarOfertaComponent } from './criar-oferta/criar-oferta.component';


@NgModule({
  declarations: [
    ListagemCatalogoComponent,
    CriarOfertaComponent
  ],
  imports: [
    CommonModule,
    CatalogoRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    ItemModule
  ]
})
export class CatalogoModule { }
