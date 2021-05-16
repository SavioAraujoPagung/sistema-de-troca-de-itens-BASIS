import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PageNotificationService } from '@nuvem/primeng-components';
import { finalize } from 'rxjs/operators';

import { UsuarioService } from './../../services/usuario.service';

@Component({
  selector: 'app-listagem-page',
  templateUrl: './listagem-page.component.html',
  styleUrls: ['./listagem-page.component.css']
})
export class ListagemPageComponent implements OnInit {

  usuarios: any[] = [];
  displayModal: boolean = false;
  submit: boolean = false;
  form: FormGroup;
  isEditing: boolean = false;

  constructor(
    private usuarioService: UsuarioService,
    private fb: FormBuilder,
    private notification: PageNotificationService) {

  }

  ngOnInit(): void {
    this.iniciarForm();
    this.buscarTodos();
  }

  buscarTodos(){
    this.usuarioService.buscarTodos().subscribe(
      (usuarios) => {
        this.usuarios = usuarios;
      }
    )
  }

  abrirModal(){
    this.displayModal=true;
  }

  editar(id){
    this.isEditing=true;
    this.usuarioService.buscarPorId(id).subscribe(
      (usuario)=>{
        this.displayModal=true;
        this.form.patchValue({
          ...usuario,
          data: new Date(usuario.data)
        }); 
      })
  }

  salvar(){
    this.submit = true
    if (this.isEditing){
      this.usuarioService.atualizar(this.form.value).pipe(
        finalize(()=>{
          this.submit = false;
          this.fecharModal();
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
    }else{
      this.usuarioService.salvar(this.form.value).pipe(
        finalize(()=>{
          this.fecharModal();
          this.submit = false;
        })
      ).subscribe(
        (usuario) => {
          this.buscarTodos();
          this.notification.addSuccessMessage("Usuario Cadastrado com sucesso");
        },
        ()=>{
          this.notification.addErrorMessage("Erro ao cadastrar usuario");
        }
      );
    }
  }

  fecharModal(){
    this.form.reset();
    this.displayModal = false;
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
    this.usuarioService.excluir(id).subscribe(
      ()=>{
        this.buscarTodos();
        this.notification.addSuccessMessage("Usuario excluído");
      },
      ()=>{
        this.buscarTodos();
        this.notification.addErrorMessage("Erro ao excluir");
      }
    )
  }
}
