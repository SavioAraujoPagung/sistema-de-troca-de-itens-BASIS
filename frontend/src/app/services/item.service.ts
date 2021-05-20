import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Item } from './../shared/models/item.model';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private api = `${environment.apiUrl}/item`

  constructor(private http: HttpClient) { }

  listar(){

  }
  
  obterPorId(id){
    return this.http.get<Item>(this.api + id);
  }

  salvar(item){
    return this.http.post(this.api, item);
  }

  alterar(){

  }

  deletar(){

  }

}
