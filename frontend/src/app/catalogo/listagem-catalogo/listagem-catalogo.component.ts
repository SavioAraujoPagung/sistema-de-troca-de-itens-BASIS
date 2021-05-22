import { Component, OnInit, ViewChild } from '@angular/core';

import { finalize } from 'rxjs/operators';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { SelectItem } from 'primeng';

import { ItemService } from './../../services/item.service';
import { Oferta } from './../../shared/models/oferta.model';
import { Usuario } from './../../shared/models/usuario.model';
import { Item } from './../../shared/models/item.model';
import { CriarOfertaComponent } from './../criar-oferta/criar-oferta.component';

@Component({
  selector: 'app-listagem-catalogo',
  templateUrl: './listagem-catalogo.component.html',
  styleUrls: ['./listagem-catalogo.component.css']
})
export class ListagemCatalogoComponent implements OnInit {
  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';
  @ViewChild('dialogOferta') dialogOferta: CriarOfertaComponent;

  itens: Item[];

  sortOptions: SelectItem[];
  sortKey: string;
  sortField: string;
  sortOrder: number;

  displayOferta: boolean = false;
  itemSource: Item[];
  itemTarget: Item[];
  novaOferta: Oferta = new Oferta;
  usuarioLogado: Usuario;

  constructor(
    private itemService: ItemService,
    ) { }

  ngOnInit() {
      this.buscarTodos();

      this.sortOptions = [
          {label: 'Nome A->Z', value: 'nome'},
          {label: 'Nome Z->A', value: '!nome'},
          {label: 'Categoria A->Z', value: 'categoriaId'},
          {label: 'Categoria Z->A', value: '!categoriaId'}
      ];
  }

  buscarTodos(){
    this.usuarioLogado = JSON.parse(localStorage.getItem("usuario"));
    this.blockUI.start(this._mensagemBlockUi);
    this.itemService.listarDisponivelExcetoUsuario(this.usuarioLogado.id).pipe(
      finalize(()=>{
        this.blockUI.stop();
      })
    ).subscribe(
      (itens) => {
        this.itens = itens;
        this.itens = this.montarImagens(this.itens);
      }
    )
  }

  onSortChange(event) {
    let value = event.value;

    if (value.indexOf('!') === 0) {
        this.sortOrder = -1;
        this.sortField = value.substring(1, value.length);
    }
    else {
        this.sortOrder = 1;
        this.sortField = value;
    }
  }

  montarImagens(itens: Item[]){
    itens.forEach(element => {
      let formatoImagem = "data:image/jpg;base64,";
      let imagem = formatoImagem.concat(element.imagem);
      element.imagem = imagem;
    });
    return itens;
  }

  ofertar(itemDesejadoId){
    this.dialogOferta.showOfertaDialog(itemDesejadoId);
  }

}
