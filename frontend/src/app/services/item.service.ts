import { Item } from './../shared/models/item.model';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private api = `${environment.apiUrl}/item`

  constructor(private http: HttpClient) { }

  listar(){

  }
  
  obterPorId(){

  }

  salvar(item){
    return this.http.post(this.api, item);
  }

  alterar(){

  }

  deletar(){

  }

}
