import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  private baseApi: string = 'http://localhost:8080/api/v1/file';

  constructor(private http: HttpClient) { }

  uploadFile(file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    return this.http.post<any>(`${this.baseApi}/upload`, formData);
  }

  getImage(imageId: string): Observable<any> {
    return this.http.get<any>(`${this.baseApi}/files/${imageId}`, {responseType: 'blob' as 'json'});
  }
}
