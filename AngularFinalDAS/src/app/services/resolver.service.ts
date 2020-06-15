import { Injectable } from '@angular/core';
import { Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { CategoriasService } from 'src/app/services/indec/categorias.service';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { ResolvedCategorias } from './../models/resolved-categories.model';
import { ProductosService } from './indec/productos.service';
import { ResolvedProductos } from '../models/resolved-productos.model';
/*
@Injectable()
export class CategoriasResolverService implements Resolve<ResolvedCategorias> {

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ResolvedCategorias> {
    return this.sCat.getCategoriaResponse()
                      .pipe(
                        map((categorias) => new ResolvedCategorias(categorias)),
                        catchError((err: any) => of(new ResolvedCategorias(null, err)))
                        );
  }

  constructor(
    private sCat: CategoriasService
  ) {}
}

@Injectable()
export class ProductosResolverService implements Resolve<ResolvedProductos> {

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ResolvedProductos> {
    return this.sPro.getProductosResponse()
                      .pipe(
                        map((categorias) => new ResolvedProductos(categorias)),
                        catchError((err: any) => of(new ResolvedProductos(null, err)))
                        );
  }

  constructor(
    private sPro: ProductosService
  ) {}
}
*/
