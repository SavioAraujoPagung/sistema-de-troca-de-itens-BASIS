import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PageNotificationService } from '@nuvem/primeng-components';

import { UsuarioListagem } from './../../shared/models/usuario-listagem.model';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { finalize } from 'rxjs/operators';

import { UsuarioService } from './../../services/usuario.service';

@Component({
  selector: 'app-listagem-page',
  templateUrl: './listagem-page.component.html',
  styleUrls: ['./listagem-page.component.css']
})
export class ListagemPageComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

  usuarios: UsuarioListagem[] = [];
  displayModal: boolean = false;
  submit: boolean = false;
  form: FormGroup;
  isEditing: boolean = false;

  constructor(
    private usuarioService: UsuarioService,
    private fb: FormBuilder,
    private notification: PageNotificationService
    ) {}

  ngOnInit(): void {
    this.iniciarForm();
    this.buscarTodos();
  }

  buscarTodos(){
    this.blockUI.start(this._mensagemBlockUi);
    this.usuarioService.buscarTodos().pipe(
      finalize(()=>{
        this.blockUI.stop();
      })
    ).subscribe(
      (usuarios) => {
        this.usuarios = usuarios;
      }
    )
  }

  abrirModal(){
    this.displayModal=true;
  }

  editar(id){
    this.isEditing = true;
    this.usuarioService.buscarPorId(id).subscribe(
      (usuario) => {
        this.displayModal = true;
        this.form.patchValue({
          ...usuario,
          data: new Date(usuario.data)
        });
      }
    )
  }

  salvar(){
    this.blockUI.start(this._mensagemBlockUi);
    this.submit = true;
    if (this.isEditing) { this.alterarDadosUsuario(); }
    else{ this.salvarNovoUsuario(); }
  }

  alterarDadosUsuario(){
    this.usuarioService.atualizar(this.form.value).pipe(
      finalize(()=>{
        this.submit = false;
        this.fecharModal();
        this.blockUI.stop();
      })
    ).subscribe(
      () => {
        this.buscarTodos();
        this.notification.addSuccessMessage("Usuario atualizado com sucesso");
      },
      ()=>{
        this.notification.addErrorMessage("Erro ao atualizar usuario");
      }
    );
  }

  salvarNovoUsuario(){
    this.usuarioService.salvar(this.form.value).pipe(
      finalize(()=>{
        this.submit = false;
        this.fecharModal();
        this.blockUI.stop();
      })
    ).subscribe(
      () => {
        this.buscarTodos();
        this.notification.addSuccessMessage("Usuario Cadastrado com sucesso");
      },
      ()=>{
        this.notification.addErrorMessage("Erro ao cadastrar usuario");
      }
    );
  }

  fecharModal(){
    this.form.reset();
    this.displayModal = false;
    this.isEditing = false;
  }

  iniciarForm(){
    this.form = this.fb.group({
      id: [null],
      nome: [null, [Validators.required]],
	    cpf: [null, [Validators.required, Validators.maxLength(11), Validators.minLength(11)]],
	    email: [null, [Validators.required, Validators.email]],
	    data: [null, [Validators.required]]
    })
  }

  excluir(id){
    this.blockUI.start(this._mensagemBlockUi);
    this.usuarioService.excluir(id).pipe(
      finalize(() => {
        this.buscarTodos();
        this.blockUI.stop();
      })
    ).subscribe(
      () => { this.notification.addSuccessMessage("Usuario excluído"); },
      () => { this.notification.addErrorMessage("Erro ao excluir"); }
    )
  }

  labelHeader(){ return this.isEditing ? 'Atualizar' : 'Cadastro'; }

  labelSubmit(){ return this.isEditing ? 'Salvar' : 'Criar'; }

  blockUiDelayPadrao(){
    this.blockUI.start(this._mensagemBlockUi); // Start blocking

    setTimeout(() => {
      this.blockUI.stop(); // Stop blocking
    }, 2000);
  }
}
