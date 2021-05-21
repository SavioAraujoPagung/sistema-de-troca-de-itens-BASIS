import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from './../shared/shared.module';
import { MinhasOfertasRoutingModule } from './minhas-ofertas-routing.module';
import { MinhasOfertasListagemComponent } from './minhas-ofertas-listagem/minhas-ofertas-listagem.component';


@NgModule({
  declarations: [MinhasOfertasListagemComponent],
  imports: [
    CommonModule,
    MinhasOfertasRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class MinhasOfertasModule { }
