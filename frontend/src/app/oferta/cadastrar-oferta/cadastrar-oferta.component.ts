import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { OfertaService } from './../../services/oferta.service';
import { Item } from './../../shared/models/item.model';
import { ItemService } from 'src/app/services/item.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadastrar-oferta',
  templateUrl: './cadastrar-oferta.component.html',
  styleUrls: ['./cadastrar-oferta.component.css']
})
export class CadastrarOfertaComponent implements OnInit {

  itemSource: Item[];
  itemAlvo: Item[];
  form: FormGroup;

    constructor(
      private itemServico: ItemService,
      private ofertaServico: OfertaService,
      private fb: FormBuilder
      ) { }

    ngOnInit() {
      this.iniciarForm();
      this.iniciarListas();
    }

    iniciarForm(){
      this.form = this.fb.group({
        id: [null],
        itemId: [null, [Validators.required]],
        usuarioOfertanteId: [null, [Validators.required]],
        itensOfertados: [null, [Validators.required]],
        situacaoId: [null, [Validators.required]]
      })
    }

    iniciarListas(){
      this.itemServico.listar().subscribe(
        (itens) => {
          this.itemSource = itens;
          this.itemSource = this.montarImagem(this.itemSource);
          this.itemAlvo = [];
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
}
