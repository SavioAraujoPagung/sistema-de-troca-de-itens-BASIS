import { finalize } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';

import { BlockUI, NgBlockUI } from 'ng-block-ui';

import { Oferta } from './../../shared/models/oferta.model';
import { Item } from 'src/app/shared/models/item.model';
import { ItemService } from 'src/app/services/item.service';
import { OfertaService } from './../../services/oferta.service';

@Component({
  selector: 'app-listagem-itens-ofertados',
  templateUrl: './listagem-itens-ofertados.component.html',
  styleUrls: ['./listagem-itens-ofertados.component.css']
})
export class ListagemItensOfertadosComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

  itensOfertados: Item[];
  isVisible: boolean = false;
  oferta: Oferta = new Oferta();
  contador: number = 0;

  constructor(
    private ofertaService: OfertaService,
    private itemService: ItemService
  ) { }

  ngOnInit(): void {
  }

  showDisplay(id) {
    this.blockUI.start(this._mensagemBlockUi);
    this.itensOfertados = [];
    this.contador = 0;
    this.ofertaService.obterPorId(id).subscribe(
      (data) => {
        this.oferta = data;
        this.obterItensOfertados();
      }
    );
  }

  obterItensOfertados(){
    if (this.contador < this.oferta.itensOfertados.length) {
      this.itemService.obterPorId(this.oferta.itensOfertados[this.contador]).pipe(
        finalize(() => {
          this.blockUI.stop();
        })
      ).subscribe(
        (data) => {
          data.imagem = this.montarImagem(data.imagem);
          this.itensOfertados.push(data);
          this.contador++;
          this.obterItensOfertados();
        }
      )
    } else {
      this.isVisible = true;
      this.blockUI.stop();
    }
  }

  montarImagem(base: string){
    let formatoImagem = "data:image/jpg;base64,";
    return formatoImagem.concat(base);
  }

}
