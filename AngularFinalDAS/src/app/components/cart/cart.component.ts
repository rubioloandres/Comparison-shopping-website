import { Component, OnInit } from '@angular/core';
import { Producto } from '../../interfaces/producto';
import { DataSharingService } from 'src/app/services/datasharing.service';
import { Ubicacion } from 'src/app/interfaces/ubicacion';
import { MatDialog } from '@angular/material';
import { DialogLocationComponent } from '../location/location.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})

export class CartComponent implements OnInit {
  carrito: Producto[] = new Array<Producto>();
  displayedColumns = ['item', 'nombre', 'categoria', 'accion'];
  ubicacion: Ubicacion;

  constructor(
    private data: DataSharingService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.carrito = this.obtenerProductos();
  }

  obtenerProductos(): Producto[] {
    const carrito: Producto[] = JSON.parse(sessionStorage.getItem('carrito'));
    return carrito;
  }

  guardarProductos(prods: Producto[]): void {
    sessionStorage.setItem('carrito', JSON.stringify(prods));
  }

  removerTodosLosProductos(): void {
    this.carrito = [];
    sessionStorage.setItem('carrito', JSON.stringify(this.carrito));
  }

  remover(prod: Producto): void {
    this.carrito = this.obtenerProductos().filter(p => p.codigoDeBarras !== prod.codigoDeBarras);
    this.guardarProductos(this.carrito);
  }

  estaVacio(): boolean {
    return this.obtenerProductos().length === 0;
  }

  agregar(prod: Producto): void {
    const carrito: Producto[] = this.obtenerProductos();
    if (!this.estaEnElCarrito(prod)) {
      carrito.push(prod);
      this.carrito = carrito;
      sessionStorage.setItem('carrito', JSON.stringify(this.carrito));
      return;
    }
  }

  estaEnElCarrito(prod: Producto): boolean {
    const productoEncontrado = this.obtenerProductos().find(p => p.codigoDeBarras === prod.codigoDeBarras);
    if (productoEncontrado !== undefined) {
      return true;
    } else {
      return false;
    }
  }

  compararPrecios(): void {
    this.data.compararPrecios(this.obtenerProductos());
  }

  getDisplayedColumns(): string[] {
    return this.displayedColumns;
  }



  // TODO: QUE ONDA CON LA UBICACION ACA
  registrarUbicacion() {
    console.log('SE NECESITA DETERMINAR UNA UBICACION');
    const dialogRef = this.dialog.open(DialogLocationComponent, {
      width: '500px',
      data: { data: 'ubic___' }
    });
  }

  cargarUbicacion() {
    const ubLS = sessionStorage.getItem('ubicacion');
    if (ubLS == null || ubLS.length < 2) {
      this.ubicacion = undefined;
      return;
    } else {
      this.ubicacion = JSON.parse(ubLS);
    }
  }

  hayUbicacionRegistrada(): boolean {
    const ubLS = sessionStorage.getItem('ubicacion');
    if (ubLS == null || ubLS.length < 2) {
      return false;
    } else {
      return true;
    }
  }


}
