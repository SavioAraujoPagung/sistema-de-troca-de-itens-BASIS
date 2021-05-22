import { Router } from '@angular/router';
import { CategoriaService } from './../../services/categoria.service';
import { ItemService } from 'src/app/services/item.service';
import { Item } from './../../shared/models/item.model';
import { Categoria } from './../../shared/models/categoria.model';
import { AlterarItensComponent } from 'src/app/inventario/alterar-itens/alterar-itens.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { SelectItem } from 'primeng';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-listar-inventario',
  templateUrl: './listar-inventario.component.html',
  styleUrls: ['./listar-inventario.component.css']
})
export class ListarInventarioComponent implements OnInit {


  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';
  @ViewChild('dialogItem') dialogItem: AlterarItensComponent;

  usuarioId: number;
  categoria: Categoria;
  categorias: Categoria[] = [];
  itens: Item[];
  form: FormGroup;
  selectedItem: Item;
  itemSelecionado: Item;
  displayDialog: boolean;
  sortOptions: SelectItem[];
  sortKey: string;
  sortField: string;
  sortOrder: number;


  constructor(
    private itemService: ItemService,
    private fb: FormBuilder,
    private categoriaService: CategoriaService,
    private router: Router
    ) { }

  ngOnInit() {
      this.iniciarForm();
      this.buscarTodos();
      this.buscarCategorias();
      console.log("categorias" );
      console.log(this.categorias);

      this.sortOptions = [
          {label: 'Nome A->Z', value: 'nome'},
          {label: 'Nome Z->A', value: '!nome'},
          {label: 'Categoria A->Z', value: 'categoriaId'},
          {label: 'Categoria Z->A', value: '!categoriaId'}
      ];
  }

  buscarTodos(){
    this.usuarioId = JSON.parse(localStorage.getItem("usuario")).id;
    
    this.blockUI.start(this._mensagemBlockUi);
    this.itemService.inventarioListar(this.usuarioId).pipe(
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

  buscarCategorias(){
    this.categoriaService.buscarTodos().pipe(
      finalize(()=>{
        this.blockUI.stop();
      })
    ).subscribe(
      (categorias) => {
        this.categorias = categorias;
        console.log(categorias+ "buscar categorias");
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
    this.categorias.forEach(cat =>{

      if(cat.id == item.categoriaId){
      }
    })
    this.selectedItem = item;
    this.displayDialog = true;
    event.preventDefault();
  }

  alterarItem(event: Event, item: Item) {
    this.itemSelecionado = item;
    this.router.navigate(['alterar']);
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

  alterar(){
    this.dialogItem.abrir(this.selectedItem);
  }
}