import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Producto } from '../../interfaces/producto';
import { catchError } from 'rxjs/operators';
import { CriterioBusquedaProducto} from 'src/app/interfaces/criterios';

@Injectable()
export class ProductosService {

  constructor(private http: HttpClient) { }

  public buscarProductos(critops: CriterioBusquedaProducto): Observable<Producto[]> {

    const httpParams: HttpParams = this.crearHttParams(critops);
    return (
      this.http.get<Producto[]>(environment.webAPI + 'productos', {params: httpParams})
    )
      .pipe(catchError(err => {
        console.log('Error al obtener productos', err);
        return throwError(err);
      }));
  }


  crearHttParams(critops: CriterioBusquedaProducto): HttpParams {

    const aggregarCategoria = (params: HttpParams): HttpParams => {
      if (critops.idCategoria) {
        return params.set('idcategoria', critops.idCategoria.toString());
      } else {
        return params;
      }
    };

    const aggregarMarcas = (params: HttpParams): HttpParams => {
      if (critops.marcas) {
        return params.set('marcas', critops.marcas.toString());
      } else {
        return params;
      }
    };

    const aggregarPalabraClave = (params: HttpParams): HttpParams => {
      if (critops.palabraclave) {
        return params.set('palabraclave', critops.palabraclave);
      } else {
        return params;
      }
    };

    return aggregarPalabraClave(aggregarMarcas(aggregarCategoria(new HttpParams())));

    }
}
