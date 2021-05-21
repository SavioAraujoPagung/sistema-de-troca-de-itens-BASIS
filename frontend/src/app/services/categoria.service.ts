import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Categoria } from './../shared/models/categoria.model';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private api = `${environment.apiUrl}/categoria/`;

  constructor(private http: HttpClient) { }

  buscarTodos(){
    return this.http.get<Categoria[]>(this.api);
  }
  obterPorId(id){
    return this.http.get<Categoria>(this.api + id);
  }
}
