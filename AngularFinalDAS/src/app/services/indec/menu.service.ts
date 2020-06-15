import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Menu } from 'src/app/interfaces/menu';
import { CadenaSucursal } from 'src/app/interfaces/cadena';

@Injectable()
export class MenuService {

  constructor(
    private http: HttpClient
    ) { }

  getMenu(): Observable<Menu[]> {
    return this.http.get<Menu[]>( environment.webAPI + 'menu/')
                    .pipe(catchError(err => {
                      console.log('Error al obtener menu', err);
                      return throwError(err);
                    }));
  }

  public getPrecioPlato(codEntidadFed: string, loc: string, idPlato: number): Observable<CadenaSucursal[]> {
    const options = 'codigoentidadfederal=' + codEntidadFed + '&localidad=' + loc + '&idplato=' + idPlato;
    return this.http.get<CadenaSucursal[]>(environment.webAPI + 'comparadorplato?' + options)
                    .pipe(
                      catchError(err => {
                      console.log('Error al obtener respuesta del comparador para el plato', err);
                      return throwError(err);
                    }));
  }

}
