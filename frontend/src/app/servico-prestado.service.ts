import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServicoPrestado } from './dto/ServicoPrestado';
import { Observable } from 'rxjs';
import { ServicoPrestadoBusca } from './dto/ServicoPrestadoBusca';

@Injectable({
  providedIn: 'root'
})
export class ServicoPrestadoService {
  private baseApi: string = "http://localhost:8080/api/v1/servico";

  constructor(private http: HttpClient) { }

  salvarServico(servicoPrestado: ServicoPrestado): Observable<ServicoPrestado> {
    return this.http.post<ServicoPrestado>(`${this.baseApi}`, servicoPrestado);
  }

  pesquisarServico(nome: string, mes: number): Observable<ServicoPrestadoBusca[]> {
    const params = new HttpParams()
      .set("nome", nome)
      .set("mes", mes);

    return this.http.get<ServicoPrestadoBusca[]>(`${this.baseApi}`, { params });
  }
}
