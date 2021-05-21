import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OfertaRoutingModule } from './oferta-routing.module';
import { ItemModule } from './../item/item.module';
import { SharedModule } from './../shared/shared.module';
import { CriarOfertaComponent } from './criar-oferta/criar-oferta.component';


@NgModule({
  declarations: [CriarOfertaComponent],
  imports: [
    CommonModule,
    OfertaRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    ItemModule
  ]
})
export class OfertaModule { }
