import { Component, OnInit, ValueProvider, OnDestroy, EventEmitter } from '@angular/core';
import { Categoria } from 'src/app/interfaces/categoria';
import { DataSharingService } from 'src/app/services/datasharing.service';
import { Producto } from 'src/app/interfaces/producto';
import { Provincia } from 'src/app/interfaces/provincia';
import { Localidad } from 'src/app/interfaces/localidad';
import { FormControl } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Ubicacion } from 'src/app/interfaces/ubicacion';
import { MatButtonModule } from '@angular/material/button';
import { CriterioBusquedaProducto } from 'src/app/interfaces/criterios';
@Component({
  selector: 'app-searchfilter',
  templateUrl: './searchfilter.component.html',
  styleUrls: ['./searchfilter.component.css']
})
export class SearchfilterComponent implements OnInit, OnDestroy {

  nombreComponente = 'SearchfilterComponent';
  mode: FormControl = new FormControl('side');
  formFilterCategorias: FormControl = new FormControl();
  formFilterMarcas: FormControl = new FormControl();

  private suscripcionProductosDelCatalogo: Subscription;

  listaMarcas: string[] = new Array();
  listaCategorias: Categoria[] = new Array();
  resetFiltroCategoria: boolean;

  constructor(private data: DataSharingService) { }


  obtenerCategorias(): Categoria[] {
    const categorias: Categoria[] = JSON.parse(sessionStorage.getItem('categorias'));
    console.log(categorias);
    return categorias;
  }

  buscarPorCategoria(): void {
    const criterio = this.crearCriterioDeBusqueda(this.formFilterCategorias.value, undefined);
    if (criterio.idCategoria) {
      this.resetFiltroCategoria = false;
      this.data.changeCriterioBusquedaProducto(criterio);
    } else {
      this.resetFiltroCategoria = true;
      this.listaCategorias = this.obtenerCategorias();
      return;
    }
  }

  buscar(): void {
    const categoria: Categoria = this.formFilterCategorias.value;
    const marcas: string[] = this.formFilterMarcas.value;
    const criterio = this.crearCriterioDeBusqueda(categoria, marcas);
    if (criterio.idCategoria) {
      this.resetFiltroCategoria = false;
    } else {
      this.resetFiltroCategoria = true;
    }

    this.data.changeCriterioBusquedaProducto(criterio);
  }

  actualizarFiltros(): void {

    this.suscripcionProductosDelCatalogo = this.data.productosDelCatalogo.subscribe(ev => {
      // TODO: CUANDO UN EVENTO NO TIENE UN COMPONENTE??
      if (ev.componente) {
        if (ev.productos.length > 0) {
          // cargamos las marcas
          this.listaMarcas = [];
          const conjuntoMarcas = new Set<string>();
          ev.productos.forEach(p => conjuntoMarcas.add(p.nombreMarca));
          this.listaMarcas = Array.from(conjuntoMarcas.values());
          // si el evento vino del componente 'CategoriesComponent'
          if (ev.componente.match('CategoriesComponent') !== null) {
            const idcategoria = ev.productos[0].idCategoria;
            const categoria = this.obtenerCategorias().find(cat => cat.idCategoria === idcategoria);
            this.listaCategorias = [categoria];
            return;
          }
          // si el evento vino desde este mismo componente
          if (ev.componente.match(this.nombreComponente) !== null) {
            // hay que resetear el filtro de categorias
             if (this.resetFiltroCategoria) {
              this.listaCategorias = this.obtenerCategorias();
             }
             return;
          } else {// el evento vino de algun otro componente
            this.listaCategorias = this.obtenerCategorias();
            this.formFilterCategorias.reset();
            this.formFilterMarcas.reset();
            return;
          }
        } else {
          this.listaMarcas = [];
          return;
        }
      }
    });
  }


    crearCriterioDeBusqueda(categoria: Categoria, marcas: string[]): CriterioBusquedaProducto {
      const criterio: CriterioBusquedaProducto = { componente: this.nombreComponente };
      if (categoria !== null && categoria !== undefined) {
        criterio.idCategoria = categoria.idCategoria;
      }
      if (marcas !== null && marcas !== undefined) {
        const opTodasLasMarcas = marcas.filter(s => s === undefined).length === 1;
        if (opTodasLasMarcas) {
          return criterio;
        } else {
          criterio.marcas = marcas;
          return criterio;
        }
      }
      return criterio;
    }

    ngOnInit() {
      this.listaCategorias = this.obtenerCategorias();
      this.actualizarFiltros();
      this.resetFiltroCategoria = false;
    }

    ngOnDestroy(): void {
      this.suscripcionProductosDelCatalogo.unsubscribe();
    }
  }
