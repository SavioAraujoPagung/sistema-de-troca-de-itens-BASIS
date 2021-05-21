import { finalize } from 'rxjs/operators';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { Oferta } from './../../shared/models/oferta.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { OfertaService } from './../../services/oferta.service';
import { Item } from './../../shared/models/item.model';
import { ItemService } from 'src/app/services/item.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadastrar-oferta',
  templateUrl: './cadastrar-oferta.component.html',
  styleUrls: ['./cadastrar-oferta.component.css']
})
export class CadastrarOfertaComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

  @Input() itemDesejadoId: number = 1;
  itemSource: Item[];
  itemTarget: Item[];
  novaOferta: Oferta = new Oferta;
  usuarioLogado: any;

    constructor(
      private itemServico: ItemService,
      private ofertaServico: OfertaService
      ) { }

    ngOnInit() {
      this.blockUI.start(this._mensagemBlockUi);
      this.iniciarOferta();
      this.iniciarListas();
    }

    iniciarOferta(){
      this.usuarioLogado = JSON.parse(localStorage.getItem("usuario"));
      this.novaOferta.itemId = this.itemDesejadoId;
      this.novaOferta.usuarioOfertanteId = this.usuarioLogado.id
    }

    iniciarListas(){
      this.itemServico.listar().pipe(
        finalize(()=>{
          this.blockUI.stop();
        })
      ).subscribe(
        (itens) => {
          this.itemSource = itens;
          this.itemSource = this.montarImagem(this.itemSource);
          this.itemTarget = [];
        }
      );
    }

    montarImagem(itens: Item[]){
      itens.forEach(element => {
        let formatoImagem = "data:image/jpg;base64,";
        let imagem = formatoImagem.concat(element.imagem);
        element.imagem = imagem;
      });
      return itens;
    }

    salvar(){
      let itensOfertadosId: number[] = [];
      this.itemTarget.forEach(element => {
        itensOfertadosId.push(element.id);
      });
      this.novaOferta.itensOfertados = itensOfertadosId;
      console.log(this.novaOferta);
    }
}
