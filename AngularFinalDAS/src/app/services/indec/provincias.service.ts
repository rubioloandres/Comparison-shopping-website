import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Provincia } from '../../interfaces/provincia';
import { catchError } from 'rxjs/operators';

@Injectable()
export class ProvinciasService {

    constructor(private http: HttpClient) { }

    public getProvincias(): Observable<Provincia[]> {
      return this.http.get<Provincia[]>(environment.webAPI + 'provincias?')
                      .pipe(catchError(err => {
                        console.log('Error al obtener provincias', err);
                        return throwError(err);
                      }));
  }
}
