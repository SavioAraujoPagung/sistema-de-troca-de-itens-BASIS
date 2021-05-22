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
  ofertaAmostra: OfertaAmostra = new OfertaAmostra;
  ofertaSuporte: Oferta = new Oferta;
  usuarioLogado: Usuario;

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
        this.obterDetalhesOferta(this.ofertasOfertanteListagem);
      })
    ).subscribe(
      (data) => {
        this.ofertasOfertanteListagem = data;
      }
    )
  }

  obterDetalhesOferta(ofetasListagem: OfertaListagem[]){
    ofetasListagem.map(entidade => {
      this.ofertaService.obterPorId(entidade.id).pipe(
        finalize(() => {
          this.montarOfertaItem(this.ofertaSuporte);
        })
      ).subscribe(
        (data) => {
          this.ofertaSuporte = data
          this.ofertaAmostra.id = this.ofertaSuporte.id;
          this.ofertaAmostra.itensOfertados = this.ofertaSuporte.itensOfertados;
        }
      );
      this.ofertasOfertante.push(this.ofertaAmostra);
      console.log(this.ofertasOfertante);
    });
  }

  montarOfertaItem(base: Oferta){
    this.itemService.obterPorId(base.id).subscribe(
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
      }
    );
  }

}
