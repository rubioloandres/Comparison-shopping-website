import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Cadena, CadenaSucursal } from '../../interfaces/cadena';
import { catchError } from 'rxjs/operators';
import { ErrorManager } from '../handleError.service';

@Injectable()
export class CadenasService {

    constructor(
      private http: HttpClient,
      private errManager: ErrorManager
      ) { }

    public getCadenas(): Observable<Cadena[]> {
      return this.http.get<Cadena[]>(environment.webAPI + 'cadenas?')
                      .pipe(catchError(err => {
                        console.log('Error al obtener cadenas', err);
                        return throwError(err);
                      }));
    }

    public getComparacionINDEC(codEntidadFed: string, loc: string, cods: string): Observable<CadenaSucursal[]> {
      const options = 'codigoentidadfederal=' + codEntidadFed + '&localidad=' + loc + '&codigos=' + cods;
      return this.http.post<CadenaSucursal[]>(environment.webAPI + 'comparador?' + options, 'none')
                      .pipe(
                        catchError(err => {
                        console.log('Error al obtener respuesta del comparador', err);
                        return throwError(err);
                      }));
    }
}
