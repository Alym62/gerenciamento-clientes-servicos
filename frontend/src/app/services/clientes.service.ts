import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../dto/Clientes';
import { PageResponse } from '../config/IPageConfig';

@Injectable({
  providedIn: 'root',
})
export class ClientesService {
  private baseApi: string = 'http://localhost:8080/api/v1/cliente';

  constructor(private http: HttpClient) { }

  listarCliente(page: number, size: number): Observable<PageResponse<Cliente>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<PageResponse<Cliente>>(`${this.baseApi}`, { params });
  }

  listarClienteParaOServico(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.baseApi}/listar`);
  }

  buscarPorId(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.baseApi}/${id}`);
  }

  listarTotalDeClientesNoSistema(): Observable<number> {
    return this.http.get<number>(`${this.baseApi}/todos`);
  }

  listarMediaMensalDeClientesNoSistema(): Observable<number> {
    return this.http.get<number>(`${this.baseApi}/media`);
  }

  cadastrarCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.baseApi}`, cliente);
  }

  atualizarCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.baseApi}/${cliente.id}`, cliente);
  }

  deletarCliente(id: number): Observable<Cliente> {
    return this.http.delete<Cliente>(`${this.baseApi}/${id}`);
  }
}
