import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

// Interfaz con los datos que devuelve el backend
export interface Cliente {
  primerNombre: string;
  segundoNombre: string;
  primerApellido: string;
  segundoApellido: string;
  direccion: string;
  telefono: string;
  ciudad: string;
}


@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private baseUrl = 'http://localhost:8090/api/clientes'; // Ajusta si cambias el puerto o ruta

  constructor(private http: HttpClient) {}

  getCliente(tipo: string, numero: string): Observable<Cliente> {
    const url = `${this.baseUrl}?tipo=${tipo}&numero=${numero}`;
    return this.http.get<Cliente>(url).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    // Aquí podrías mapear diferentes tipos de error si quieres
    console.error('Error recibido del backend:', error);
    return throwError(() => error);
  }
}
