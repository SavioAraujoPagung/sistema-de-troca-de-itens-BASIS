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
  private form: FormGroup;
  private categorias: Categoria[] = [];

  constructor(
    private itensServices: ItemService,
    private categoriaService: CategoriaService,
    private formBuilder: FormBuilder,
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

  salvar(){
    console.log(this.form.value);
  }
}
