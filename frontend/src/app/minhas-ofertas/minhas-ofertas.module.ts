import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from './../shared/shared.module';
import { MinhasOfertasRoutingModule } from './minhas-ofertas-routing.module';
import { MinhasOfertasListagemComponent } from './minhas-ofertas-listagem/minhas-ofertas-listagem.component';
import { ListagemItensOfertadosComponent } from './listagem-itens-ofertados/listagem-itens-ofertados.component';
import { PorMimComponent } from './por-mim/por-mim.component';
import { ParaMimComponent } from './para-mim/para-mim.component';


@NgModule({
  declarations: [MinhasOfertasListagemComponent, ListagemItensOfertadosComponent, PorMimComponent, ParaMimComponent],
  imports: [
    CommonModule,
    MinhasOfertasRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class MinhasOfertasModule { }
