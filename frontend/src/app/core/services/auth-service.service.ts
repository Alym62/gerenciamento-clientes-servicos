import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from 'src/app/shared/models/dto/Usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly urlAuth: string = "http://localhost:8080/api/v1/auth";

  constructor(private http: HttpClient) { }

  registrarUsuario(usuario: Usuario): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.post<any>(`${this.urlAuth}/registro`, usuario).subscribe(
        (data) => resolve(data),
        (error) => reject(error),
      );
    }); 
  }

  loginUsuario(usuario: Usuario): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.post<any>(`${this.urlAuth}/login`, usuario).subscribe(
        (data) => resolve(data),
        (error) => reject(error),
      );
    });
  }
}
