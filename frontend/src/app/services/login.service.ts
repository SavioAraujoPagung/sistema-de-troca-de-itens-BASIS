import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Usuario } from '../shared/models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private api = `${environment.apiUrl}/login/`;

  constructor(private http: HttpClient) { }

  login(credentials): Observable<Usuario>{
    return this.http.post<Usuario>(this.api, credentials);
  }

  buscarPorToken(token): Observable<Usuario>{
    return this.http.get<Usuario>(this.api + token);
  }
}
