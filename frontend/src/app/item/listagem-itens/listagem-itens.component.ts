import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PageNotificationService } from '@nuvem/primeng-components';

import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { SelectItem } from 'primeng';
import { finalize } from 'rxjs/operators';
import {DataViewModule} from 'primeng/dataview';

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

  constructor(
    private itemService: ItemService,
    private fb: FormBuilder,
    private notification: PageNotificationService
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

  selectCar(event: Event, item: Item) {
    this.selectedItem = item;
    this.displayDialog = true;
    event.preventDefault();
  }

  onDialogHide() {
    this.selectedItem = null;
  }

  /*
  selectCar(event: Event, car: Item) {
      this.selectedItem = car;
      this.displayDialog = true;
      event.preventDefault();
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

  onDialogHide() {
      this.selectedItem = null;
  }
  */

}
