import { ItemModule } from './../item/item.module';
import { SharedModule } from './../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OfertaRoutingModule } from './oferta-routing.module';
import { CadastrarOfertaComponent } from './cadastrar-oferta/cadastrar-oferta.component';


@NgModule({
  declarations: [CadastrarOfertaComponent],
  imports: [
    CommonModule,
    OfertaRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    ItemModule
  ]
})
export class OfertaModule { }
