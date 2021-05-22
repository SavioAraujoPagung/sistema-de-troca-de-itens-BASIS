import { UsuarioService } from './../../services/usuario.service';
import { ItemService } from './../../services/item.service';
import { OfertaAmostra } from './../../shared/models/oferta-amosta.model';
import { Item } from 'src/app/shared/models/item.model';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { finalize } from 'rxjs/operators';
import { Oferta } from './../../shared/models/oferta.model';

import { Usuario } from './../../shared/models/usuario.model';
import { OfertaListagem } from './../../shared/models/oferta-listagem.model';
import { OfertaService } from './../../services/oferta.service';

@Component({
  selector: 'app-minhas-ofertas-listagem',
  templateUrl: './minhas-ofertas-listagem.component.html',
  styleUrls: ['./minhas-ofertas-listagem.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MinhasOfertasListagemComponent implements OnInit {

  ofertasOfertanteListagem: OfertaListagem[] = [];
  ofertasOfertante: OfertaAmostra[] = [];
  ofertaAmostra: OfertaAmostra;
  ofertaSuporte: Oferta = new Oferta;
  usuarioLogado: Usuario;
  contador: number = 0;

  responsiveOptions;

  constructor(
    private ofertaService: OfertaService,
    private itemService: ItemService,
    private usuarioService: UsuarioService
    ) { 
      this.responsiveOptions = [
          {
              breakpoint: '1024px',
              numVisible: 3,
              numScroll: 3
          },
          {
              breakpoint: '768px',
              numVisible: 2,
              numScroll: 2
          },
          {
              breakpoint: '560px',
              numVisible: 1,
              numScroll: 1
          }
      ];
  }

  ngOnInit() {
      this.obterPorOfertante();
  }

  obterPorOfertante(){
    this.usuarioLogado = JSON.parse(localStorage.getItem("usuario"));
    this.ofertaService.listarPorOfertante(this.usuarioLogado.id).pipe(
      finalize(() => {
        this.obterDetalhesOferta();
      })
    ).subscribe(
      (data) => {
        this.ofertasOfertanteListagem = data;
      }
    )
  }

  obterDetalhesOferta(){
    if (this.contador < this.ofertasOfertanteListagem.length) {
      this.ofertaAmostra = new OfertaAmostra();
      this.ofertaService.obterPorId(this.ofertasOfertanteListagem[this.contador].id).subscribe(
        (data) => {
          this.ofertaSuporte = data
          this.ofertaAmostra.id = this.ofertaSuporte.id;
          this.ofertaAmostra.itensOfertados = this.ofertaSuporte.itensOfertados;
          this.montarOfertaItem(this.ofertaSuporte);
        }
      );
    }
  }

  montarOfertaItem(base: Oferta){
    this.itemService.obterPorId(base.itemId).subscribe(
      (data) => {
        this.ofertaAmostra.item = data;
        this.montarOfertaItemImagem();
        this.montarOfertaOfertante(base);
      }
    );
  }

  montarOfertaItemImagem(){
    let formatoImagem = "data:image/jpg;base64,";
    formatoImagem = formatoImagem.concat(this.ofertaAmostra.item.imagem);
    this.ofertaAmostra.item.imagem = formatoImagem;
  }

  montarOfertaOfertante(base: Oferta){
    this.usuarioService.obterPorId(base.usuarioOfertanteId).subscribe(
      (data) => {
        this.ofertaAmostra.usuarioOfertante = data;
        this.ofertasOfertante.push(this.ofertaAmostra);
        this.reportar();
        this.contador++;
        this.obterDetalhesOferta();
      }
    );
  }

  reportar(){
    console.log(this.ofertasOfertante);
    console.log("CONTADOR: " + this.contador);
  }

}
