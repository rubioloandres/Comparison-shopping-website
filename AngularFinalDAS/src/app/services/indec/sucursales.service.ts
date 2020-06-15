import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Sucursal } from '../../interfaces/sucursal';
import { catchError } from 'rxjs/operators';
import { CadenaSucursal } from 'src/app/interfaces/cadena';
import { ErrorManager } from '../handleError.service';

@Injectable()
export class SucursalesService {

  constructor(
    private http: HttpClient,
    private errManager: ErrorManager
    ) { }

  public getSucursales(codEntidadFed: string, loc: string): Observable<CadenaSucursal[]> {
    const options = 'codigoentidadfederal=' + codEntidadFed + '&localidad=' + loc;
    return this.http.get<CadenaSucursal[]>(environment.webAPI + 'sucursales?' + options)
                    .pipe(catchError(err => {
                      console.log('Error al obtener sucursales', err);
                      return throwError(err);
                    }));
  }
}
