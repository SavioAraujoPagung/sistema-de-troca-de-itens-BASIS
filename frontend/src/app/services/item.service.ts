import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

import { Item } from './../shared/models/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private api = `${environment.apiUrl}/item/`

  constructor(private http: HttpClient) { }

  listar(): Observable<Item[]>{
    return this.http.get<Item[]>(this.api);
  }

  obterPorId(id): Observable<Item>{
    return this.http.get<Item>(this.api + id);
  }

  salvar(item): Observable<Item>{
    return this.http.post<Item>(this.api, item);
  }

  alterar(item): Observable<Item>{
    return this.http.put<Item>(this.api, item);
  }

  deletar(id){
    return this.http.delete(this.api + id);
  }
}
