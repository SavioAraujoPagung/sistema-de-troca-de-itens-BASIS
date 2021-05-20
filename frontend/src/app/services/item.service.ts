import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

import { Item } from '../shared/models/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private api = `${environment.apiUrl}/item/`

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Item[]>(this.api);
  }

  obterPorId(id){
    return this.http.get<Item>(this.api + id);
  }

  salvar(item){
    return this.http.post(this.api, item);
  }

  alterar(item){
    return this.http.put(this.api, item);
  }

  deletar(id){
    return this.http.delete(this.api + id);
  }
}
