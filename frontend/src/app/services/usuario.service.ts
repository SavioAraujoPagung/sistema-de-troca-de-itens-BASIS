import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { UsuarioListagem } from './../shared/models/usuario-listagem.model';
import { Usuario } from './../shared/models/usuario.model';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private api = `${environment.apiUrl}/usuario/`;

  constructor(private http: HttpClient) { }

  buscarPorId(id){
    return this.http.get<Usuario>(this.api + id);
  }

  buscarTodos(){
    return this.http.get<UsuarioListagem[]>(this.api);
  }

  salvar(usuario){
    return this.http.post(this.api, usuario);
  }

  atualizar(usuario){
    return this.http.put(this.api, usuario);
  }

  excluir(id){
    return this.http.delete(this.api + id);
  }

}
