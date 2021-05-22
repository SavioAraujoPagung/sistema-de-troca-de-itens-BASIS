import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { environment } from './../../environments/environment.prod';
import { OfertaListagem } from '../shared/models/oferta-listagem.model';
import { Oferta } from '../shared/models/oferta.model';

@Injectable({
  providedIn: 'root'
})
export class OfertaService {
  private api = `${environment.apiUrl}/oferta/`

  constructor(private http: HttpClient) { }

  listar(): Observable<OfertaListagem[]>{
    return this.http.get<OfertaListagem[]>(this.api);
  }

  listarPorOfertante(id): Observable<OfertaListagem[]>{
    return this.http.get<OfertaListagem[]>(this.api + "ofertante/" + id);
  }

  obterPorId(id): Observable<Oferta>{
    return this.http.get<Oferta>(this.api + id);
  }

  salvar(oferta): Observable<Oferta>{
    return this.http.post<Oferta>(this.api, oferta);
  }

  alterar(oferta): Observable<Oferta>{
    return this.http.put<Oferta>(this.api, oferta);
  }

  deletar(id){
    return this.http.delete(this.api + id);
  }

  aceitar(id): Observable<Oferta>{
    return this.http.patch<Oferta>(this.api + "/aceitar" + id, null);
  }

  cancelar(id): Observable<Oferta>{
    return this.http.patch<Oferta>(this.api + "/cancelar" + id, null);
  }

  recusar(id): Observable<Oferta>{
    return this.http.patch<Oferta>(this.api + "/recusar" + id, null);
  }
}
