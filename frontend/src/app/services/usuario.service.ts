import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  buscarPorId(id){
    return this.http.get<any>('api/usuario/' + id);
  }

  buscarTodos(){
    return this.http.get<any[]>('api/usuario');
  }

  salvar(usuario){
    return this.http.post<any>('api/usuario', usuario);
  }

  atualizar(usuario){
    return this.http.put<any>('api/usuario', usuario);
  }

  excluir(id){
    return this.http.delete('api/usuario/' + id);
  }

}
