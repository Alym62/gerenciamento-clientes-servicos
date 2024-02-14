import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../dto/Usuario';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly urlAuth: string = "http://localhost:8080/api/v1/auth";

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) { }

  registrarUsuario(usuario: Usuario): Observable<any> {
    return this.http.post<any>(`${this.urlAuth}/registro`, usuario);
  }

  loginUsuario(usuario: Usuario): Observable<any> {
    return this.http.post<any>(`${this.urlAuth}/login`, usuario);
  }
}
