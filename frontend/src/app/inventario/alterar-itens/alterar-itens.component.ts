import { CategoriaService } from './../../services/categoria.service';
import { ItemService } from 'src/app/services/item.service';
import { Categoria } from './../../shared/models/categoria.model';
import { Item } from './../../shared/models/item.model';

import { finalize } from 'rxjs/operators';
// import { Item } from '../shared/models/item.model';
// import { finalize } from 'rxjs/operators';
// import { PageNotificationService } from '@nuvem/primeng-components';
// import { CategoriaService } from '../services/categoria.service';
// import { ItemService } from '../services/item.service';
// import { Categoria } from '../shared/models/categoria.model';
import { NgBlockUI, BlockUI } from 'ng-block-ui';

import { PageNotificationService } from '@nuvem/primeng-components';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-alterar-itens',
  templateUrl: './alterar-itens.component.html',
  styleUrls: ['./alterar-itens.component.css']
})
export class AlterarItensComponent implements OnInit {

  displayBasic: boolean = false; 
  item: Item; 

  @Output() displayBasicClose: EventEmitter<boolean> = new EventEmitter();
  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

  private categoria: Categoria;
  private form: FormGroup;
  private categorias: Categoria[] = [];
  private imagem: any;
  private usuarioLogado: any;

  constructor(
    private itensServices: ItemService,
    private categoriaService: CategoriaService,
    private formBuilder: FormBuilder,
    private notification: PageNotificationService
  ) { }

  ngOnInit(): void {
    this.iniciarForm();
  }

  iniciarForm(){
    this.form = this.formBuilder.group({
      id: [null],
	    descricao: [null, [Validators.required]],
	    disponibilidade: [null, [Validators.required]],
	    nome: [null, [Validators.required]],
      imagem: [null, [Validators.required]],
      usuarioId: [null, [Validators.required]],
      categoriaId: [null, [Validators.required]]
    });
  }

  buscarCategorias(){
    this.categoriaService.buscarTodos().pipe(
      finalize(()=>{
        this.blockUI.stop();
      })
    ).subscribe(
      (categorias) => {
        this.categorias = categorias;
        console.log(this.categorias);
      }
    )
  }

  uploadImagem(event){
    let fileReader = new FileReader();
    let file = event.currentFiles[0];
    
    fileReader.onloadend = () => {
      this.imagem = fileReader.result;

      let blob = this.imagem.split(",");
      
      this.form.patchValue({ imagem: blob[1] });
    }
    fileReader.readAsDataURL(file);
  }

  salvar(){
    this.usuarioLogado = JSON.parse(localStorage.getItem("usuario"));
    this.form.value.usuarioId = this.usuarioLogado.id;
    this.blockUI.start(this._mensagemBlockUi);
    this.itensServices.salvar(this.form.value).pipe(
      finalize(()=>{
        this.blockUI.stop();
        this.fecharModal();
      })
    ).subscribe(
      () => {
        this.notification.addSuccessMessage("Item alterado com sucesso");
      },
      ()=>{
        this.notification.addErrorMessage("Erro ao alterar usuario");
      }
    );
  }

  montarImagem(item: Item){
    let formato = "data:image/jpg;base64,";
    let img = formato.concat(item.imagem);
    item.imagem = img;
    return item;
  }

  fecharModal(){
    this.displayBasicClose.emit(false)
  }

  abrir(item: Item){
    this.item = this.montarImagem(item);
    this.displayBasic = true;
    this.form.patchValue(item);
  }


}
