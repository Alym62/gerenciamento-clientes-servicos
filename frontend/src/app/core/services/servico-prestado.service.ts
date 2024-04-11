import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServicoPrestado } from 'src/app/shared/models/dto/ServicoPrestado';
import { Observable } from 'rxjs';
import { ServicoPrestadoBusca } from 'src/app/shared/models/dto/ServicoPrestadoBusca';

@Injectable({
  providedIn: 'root'
})
export class ServicoPrestadoService {
  private baseApi: string = "http://localhost:8080/api/v1/servico";

  constructor(private http: HttpClient) { }

  salvarServico(servicoPrestado: ServicoPrestado, file: File): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders()
    }
    const formData: FormData = new FormData()
    formData.append('data', new Blob([JSON.stringify(servicoPrestado)], {
        type: 'application/json'
      }))
    formData.append('file', file)
    return this.http.post<any>(`${this.baseApi}`, formData, httpOptions);
  }

  pesquisarServico(nome: string, mes: number): Observable<ServicoPrestadoBusca[]> {
    const params = new HttpParams()
      .set("nome", nome)
      .set("mes", mes);

    return this.http.get<ServicoPrestadoBusca[]>(`${this.baseApi}`, { params });
  }

  valorUltimoServico(): Observable<number> {
    return this.http.get<number>(`${this.baseApi}/valor`);
  }
}
