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
  ofertasOfertante: Oferta[] = [];
  usuarioLogado: Usuario;

  responsiveOptions;

  constructor(private ofertaService: OfertaService) { 
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
      this.ofertaService.obterPorId(entidade.id).subscribe(
        (data) => {
          this.ofertasOfertante.push(data);
        }
      );
    });
  }

  teste(){
    console.log(this.ofertasOfertanteListagem);
    console.log(this.ofertasOfertante);
  }

}
