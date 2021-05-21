import { finalize } from 'rxjs/operators';
import { Component, Input, OnChanges, OnInit } from '@angular/core';

import { BlockUI, NgBlockUI } from 'ng-block-ui';

import { Item } from './../../shared/models/item.model';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-ficha-item',
  templateUrl: './ficha-item.component.html',
  styleUrls: ['./ficha-item.component.css']
})
export class FichaItemComponent implements OnInit, OnChanges {

  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

  @Input("id") itemId;

  item: Item;

  constructor(
    private itemService: ItemService
  ) { }

  ngOnChanges(changes): void {
    if (this.itemId) {
      this.iniciarItem(); 
    }
  }

  ngOnInit(): void {
    
  }

  iniciarItem(){
    this.blockUI.start(this._mensagemBlockUi);
    this.itemService.obterPorId(this.itemId).pipe(
      finalize(() => {
        this.blockUI.stop();
      })
    ).subscribe(
      (data) => {
        this.item = data;
        this.item = this.montarImagem(this.item);
      }
    )
  }

  montarImagem(item: Item){
    let formatoImagem = "data:image/jpg;base64,";
    let imagem = formatoImagem.concat(this.item.imagem);
    item.imagem = imagem;
    return item;
  }

}
