import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from './dto/Clientes';

@Injectable({
  providedIn: 'root',
})
export class ClientesService {
  private baseApi: string = 'http://localhost:8080/api/v1/cliente';

  constructor(private http: HttpClient) {}

  listarCliente(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.baseApi}`);
  }

  cadastrarCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.baseApi}`, cliente);
  }
}
