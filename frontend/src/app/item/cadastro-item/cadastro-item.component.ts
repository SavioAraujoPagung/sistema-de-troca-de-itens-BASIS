import { PageNotificationService } from '@nuvem/primeng-components';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { finalize } from 'rxjs/operators';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { CategoriaService } from './../../services/categoria.service';
import { ItemService } from './../../services/item.service';
import { Categoria } from 'src/app/shared/models/categoria.model';


@Component({
  selector: 'app-cadastro-item',
  templateUrl: './cadastro-item.component.html',
  styleUrls: ['./cadastro-item.component.css']
})

export class CadastroItemComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

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
    this.buscarCategorias();
    this.iniciarForm();
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
      })
    ).subscribe(
      () => {
        this.notification.addSuccessMessage("Usuario Cadastrado com sucesso");
      },
      ()=>{
        this.notification.addErrorMessage("Erro ao cadastrar usuario");
      }
    );
    
  }
}
