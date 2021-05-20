import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { PageNotificationService } from '@nuvem/primeng-components';

import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { SelectItem } from 'primeng';
import { finalize } from 'rxjs/operators';

import { ItemService } from 'src/app/services/item.service';
import { Item } from 'src/app/shared/models/item.model';

@Component({
  selector: 'app-listagem-itens',
  templateUrl: './listagem-itens.component.html',
  styleUrls: ['./listagem-itens.component.css']
})
export class ListagemItensComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

  itens: Item[];
  form: FormGroup;
  selectedItem: Item;
  displayDialog: boolean;
  sortOptions: SelectItem[];
  sortKey: string;
  sortField: string;
  sortOrder: number;
  imageSource;

  constructor(
    private itemService: ItemService,
    private fb: FormBuilder,
    private notification: PageNotificationService,
    private sanitizer: DomSanitizer
    ) { }

  ngOnInit() {
      this.iniciarForm();
      this.buscarTodos();

      this.sortOptions = [
          {label: 'Nome A->Z', value: 'nome'},
          {label: 'Nome Z->A', value: '!nome'},
          {label: 'Categoria A->Z', value: 'categoriaId'},
          {label: 'Categoria Z->A', value: '!categoriaId'}
      ];
  }

  buscarTodos(){
    this.blockUI.start(this._mensagemBlockUi);
    this.itemService.listar().pipe(
      finalize(()=>{
        this.blockUI.stop();
      })
    ).subscribe(
      (itens) => {
        this.itens = itens;
        this.itens = this.montarImagem(this.itens);
      }
    )
  }

  iniciarForm(){
    this.form = this.fb.group({
      id: [null],
      nome: [null, [Validators.required]],
	    imagem: [null, [Validators.required]],
	    descricao: [null, [Validators.required]],
	    disponibilidade: [null, [Validators.required]],
	    usuarioId: [null, [Validators.required]],
	    categoriaId: [null, [Validators.required]]
    })
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

  selectItem(event: Event, item: Item) {
    this.selectedItem = item;
    this.displayDialog = true;
    event.preventDefault();
  }

  onDialogHide() {
    this.selectedItem = null;
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
