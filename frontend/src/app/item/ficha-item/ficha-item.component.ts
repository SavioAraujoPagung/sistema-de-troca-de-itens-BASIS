import { Item } from './../../shared/models/item.model';
import { ItemService } from 'src/app/services/item.service';
import { Component, Input, OnChanges, OnInit } from '@angular/core';

@Component({
  selector: 'app-ficha-item',
  templateUrl: './ficha-item.component.html',
  styleUrls: ['./ficha-item.component.css']
})
export class FichaItemComponent implements OnInit, OnChanges {

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
    this.itemService.obterPorId(this.itemId).subscribe(
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
