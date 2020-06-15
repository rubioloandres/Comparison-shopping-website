import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Localidad } from '../../interfaces/localidad';
import { catchError } from 'rxjs/operators';

@Injectable()
export class LocalidadesService {

    constructor(private http: HttpClient) { }

    public getLocalidades(): Observable<Localidad[]> {
      return this.http.get<Localidad[]>(environment.webAPI + 'localidades?')
                      .pipe(catchError(err => {
                        console.log('Error al obtener localidades', err);
                        return throwError(err);
                      }));
    }
}
