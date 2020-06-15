import { Component, OnInit, OnDestroy, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { DataSharingService } from '../../services/datasharing.service';
import { Producto } from './../../interfaces/producto';
import { CartComponent } from '../cart/cart.component';
import { Ubicacion } from 'src/app/interfaces/ubicacion';
import { DialogLocationComponent } from '../location/location.component';
import { MatDialog } from '@angular/material';
import { Subscription } from 'rxjs';
import { CriterioBusquedaProducto } from 'src/app/interfaces/criterios';
import { ProductosService } from 'src/app/services/indec/productos.service';

@Component({
  selector: 'app-catalogue',
  templateUrl: './catalogue.component.html',
  styleUrls: ['./catalogue.component.css']
})
export class CatalogueComponent implements OnInit, OnDestroy {

  listaProductos: Producto[] = new Array();

  private suscripcionCriterioBusquedaProducto: Subscription;
  private suscripcionProductosService: Subscription;
  private suscripcionUbicacion: Subscription;

  ubicacion: Ubicacion;
  criterioBusqueda: CriterioBusquedaProducto;

  constructor(
    private dataSharingService: DataSharingService,
    private productosService: ProductosService,
    private carrito: CartComponent,
    public dialog: MatDialog
  ) { }



  ngOnInit() {
    this.actualizarCatalogo();
    this.escucharUbicacion();
    this.cargarUbicacion();
  }

  escucharUbicacion() {
    this.suscripcionUbicacion = this.dataSharingService
      .currentUbicacion
      .subscribe(ub => {
        this.cargarUbicacion();
        this.actualizarCatalogo();
      });
  }

  ngOnDestroy() {
    this.suscripcionCriterioBusquedaProducto.unsubscribe();
    this.suscripcionProductosService.unsubscribe();
    this.suscripcionUbicacion.unsubscribe();
  }


  agregarAlCarrito(prod: Producto): void {
    this.carrito.agregar(prod);
  }

  removerDelCarrito(prod: Producto): void {
    this.carrito.remover(prod);
  }

  estaEnelCarrito(prod: Producto): boolean {
    return this.carrito.estaEnElCarrito(prod);
  }

  actualizarCatalogo(): void {// Reaccionamos al cambio en el criterio de busqueda del producto
    this.suscripcionCriterioBusquedaProducto =
      this.dataSharingService
        .currentCriterio
        .subscribe(criterioBusqueda => {
          // Buscamos los productos segun el criterio
          this.suscripcionProductosService =
            this.productosService
              .buscarProductos(criterioBusqueda)
              .subscribe(prds => {
                // Asignamos los nuevos productos a la lista para que la vista pueda mostrarlos
                this.listaProductos = prds;
                // Emitimos un evento que acuse que el catalogo se ha cambiado junto con el componente que emitio el cambio
                this.dataSharingService.catalogoActualizado({ productos: prds, componente: criterioBusqueda.componente });
              }, err => {
                console.log('HTTP Error Busqueda de productos ', err);
                // TODO:Manejo de error updateCatalog
              }, () => console.log('HTTP Request Busqueda de productos completed'));
        });
  }

  compararPrecio(producto: Producto): void {
    this.dataSharingService.compararPrecios([producto]);
  }


  cargarUbicacion(): void {
    const ubLS = sessionStorage.getItem('ubicacion');
    if (ubLS == null || ubLS.length < 2) {
      this.ubicacion = undefined;
      return;
    } else {
      this.ubicacion = JSON.parse(ubLS);
    }
  }

  registrarUbicacion():void {
    console.log('SE NECESITA DETERMINAR UNA UBICACION');
    const dialogRef = this.dialog.open(DialogLocationComponent, {
      width: '500px',
      data: { data: 'ubic___' }
    });
  }


}

