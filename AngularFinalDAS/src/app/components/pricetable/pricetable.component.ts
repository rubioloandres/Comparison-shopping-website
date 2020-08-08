

  import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
  import { Producto, ProductoPrecio } from './../../interfaces/producto';
  import { Sucursal, TotalSucursal, SucursalInfo, SucursalTablaPrecio } from './../../interfaces/sucursal';
  import { Cadena, CadenaSucursal } from 'src/app/interfaces/cadena';
  import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
  import { DataSharingService } from 'src/app/services/datasharing.service';
  import { Ubicacion } from 'src/app/interfaces/ubicacion';
  import { CadenasService } from 'src/app/services/indec/cadenas.service';
  import { DialogInfoSucursalComponent } from './../info/info.component';
  import { Subscription } from 'rxjs';

  @Component({
    selector: 'app-pricetable',
    templateUrl: './pricetable.component.html',
    styleUrls: ['./pricetable.component.css']
  })
  export class PricetableComponent implements OnInit, OnDestroy {

    displayedColumns: string[] = new Array();
    precioTotalSucursal: TotalSucursal[] = new Array(); // NO SE USA?
    listaSucursales: SucursalTablaPrecio[] = new Array();
    listaSucursalesOrdenadas: SucursalTablaPrecio [] = new Array();
    listaSucursalesAnterior: SucursalTablaPrecio[] = new Array();
    listaProductos: Producto[] = new Array();
    listaCadenasDisponibles: Cadena [] = new Array();
    listaCadenasNoDisponibles: CadenaSucursal [] = new Array();
    error: string;
    suscripcionProductos: Subscription;
    suscripcionCadenasService: Subscription;
    loading = true;
    ubicacion: Ubicacion;
    sucursalSeleccionada: SucursalTablaPrecio = undefined;
    indiceSucSel: number;
    screenWidth: number;
    cacheProductos: Producto[];

    agregarDatosSucursal(suc: Sucursal): SucursalTablaPrecio {
      const sucursalTablaPrecio: SucursalTablaPrecio = {
        idSucursal: suc.idSucursal,
        nombreSucursal: suc.nombreSucursal,
        direccion: suc.direccion,
        latitud: suc.latitud,
        longitud: suc.longitud,
        email: suc.email,
        telefono: suc.telefono,
        cuit: suc.cuit,
        localidad: suc.localidad,
        provincia: suc.provincia,
        codigoEntidadFederal: suc.codigoEntidadFederal,
        idCadena: suc.idCadena,
        productos: suc.productos,
        mejorOpcion: suc.mejorOpcion,
        cantidadDeProductosConPrecioMasBajo: suc.cantidadDeProductosConPrecioMasBajo,
        total: suc.total,
        imagenCadena: this.getCadena(suc.idCadena).imagenCadena,
        nombreCadena: this.getCadena(suc.idCadena).nombreCadena
      };
      return sucursalTablaPrecio;
    }

    agregarDatosSucursales(suc: Sucursal[]): SucursalTablaPrecio[] {
      return suc.map(s => this.agregarDatosSucursal(s));
    }

    esMejorPrecio(idCad: number, idSuc: number, idProd: string) {
      const sucursal: Sucursal = this.listaSucursalesAnterior
                                 .find (suc => (suc.idCadena === idCad) && (suc.idSucursal === idSuc));
      const producto = sucursal.productos.find( prod => prod.codigoDeBarras === idProd);
      if (producto !== undefined && (producto.mejorPrecio)) {
        return true;
      } else {
        return false;
      }
    }



    cadenasNoDisp() {
      if ( (this.listaCadenasNoDisponibles !== null)) {
        return true;
      }
      return false;
    }

    loadCadenas() {
      this.listaCadenasDisponibles = JSON.parse(sessionStorage.getItem('cadenas'));
    }

    loadColumns() {
      const n = this.calcularCantidadColumnas();

      this.displayedColumns = [];
      this.displayedColumns.push('item');
      let i = 1;
      while (i <= n)  {
        this.displayedColumns.push('sucursal' + i);
        i++;
      }
      console.log(this.displayedColumns);

      this.listaSucursalesOrdenadas = this.listaSucursales.sort(  (suc1, suc2) => {
        if ( suc1.cantidadDeProductosConPrecioMasBajo > suc2.cantidadDeProductosConPrecioMasBajo) {  return -1; }
        if ( suc1.cantidadDeProductosConPrecioMasBajo < suc2.cantidadDeProductosConPrecioMasBajo) {  return 1; }
        return 0;
      });

      this.listaSucursalesOrdenadas = this.listaSucursalesOrdenadas.sort( (suc1, suc2) => {
        if (suc1.cantidadDeProductosConPrecioMasBajo === suc2.cantidadDeProductosConPrecioMasBajo) {
          if (suc1.total < suc2.total) { return -1; }
          if (suc2.total > suc2.total) { return 1;  }
        }
      });

      // this.listaSucursalesOrdenadas = mejoresPrecios.concat(this.listaSucursalesOrdenadas);


      this.listaSucursalesAnterior = this.listaSucursalesOrdenadas;
      this.listaSucursalesOrdenadas = this.listaSucursalesOrdenadas.slice(0, 4);
    }

    getProductoPriceBySucursal(sucursal: SucursalTablaPrecio, idProd: string) {
      const precioProd = this.listaSucursales.find( s => s === sucursal)
                             .productos.find(p => p.codigoDeBarras === idProd).precio;
      if (precioProd === 0) {
        return 'No Disponible';
      } else {
        return '$ ' + precioProd;
      }
    }

    obtenerProductosCarrito(): Producto[] {
      const carrito: Producto[] = JSON.parse(sessionStorage.getItem('carrito'));
      return carrito;
    }

    removeProduct(idprod: string) {

      let carr = this.obtenerProductosCarrito();

      const found = carr.find(p => p.codigoDeBarras === idprod);

      if ( found !== undefined ) { // encontrado
        this.listaProductos = this.listaProductos.filter(p => p.codigoDeBarras !== idprod);

        carr = carr.filter( p => p.codigoDeBarras !== idprod );

        sessionStorage.setItem('carrito', JSON.stringify(carr));

        this.cargarUbicacion();

        if (this.listaProductos.length > 0) {

          this.listaCadenasNoDisponibles = new Array();
          this.compararPrecios(this.listaProductos);
        } else {
          this.listaCadenasNoDisponibles = new Array();
          this.displayedColumns = new Array();
        }
      } else { // no encontrado
        this.listaProductos = this.listaProductos.filter(p => p.codigoDeBarras !== idprod);
        this.displayedColumns = new Array();
      }
    }


    getCadena(id: number) {
      return this.listaCadenasDisponibles.find(cad => cad.idCadena === id);
    }

    getImagenCadena(idCad: number) {
      const cads = sessionStorage.getItem('cadenas');
      if (cads !== null) {
        const lcad: Cadena[] = JSON.parse(cads);
        return lcad.find(c => c.idCadena === idCad).imagenCadena;
      }
    }

    openDialogInfo(suc: Sucursal): void {
      const cadenaSuc = this.getCadena(suc.idCadena);
      const dialogRef = this.dialog.open(DialogInfoSucursalComponent, {
        width: '500px',
        data: {   nombreCadena: cadenaSuc.nombreCadena,
                  imagenCadena: cadenaSuc.imagenCadena,
                  nombreUbicacion: suc.nombreSucursal,
                  direccion: suc.direccion,
                  latitud: suc.latitud,
                  longitud: suc.longitud}
      });
    }

    cargarTablaPrecios() {
      this.suscripcionProductos = this.data.productosParacomparar.subscribe(productos => {
        this.compararPrecios(productos);
      });
    }

    compararPrecios(productos: Producto[]) {
      console.log('recomparando precios');
      console.log(productos);
      console.log( this.ubicacion.codigoEntidadFederal);
      console.log( this.ubicacion.localidad );

      this.listaSucursales = new Array();
      this.listaSucursalesOrdenadas = new Array();
      this.listaSucursalesAnterior = new Array();
      this.listaProductos = new Array();
      this.listaCadenasNoDisponibles  = new Array();

      this.listaProductos = productos;
      this.cacheProductos = productos;
      const codigos = new Set<string>();
      productos.forEach(p => codigos.add(p.codigoDeBarras));
      const arrcodigos = Array.from(codigos.values());
      this.suscripcionCadenasService = this.sCad
      .getComparacionINDEC(this.ubicacion.codigoEntidadFederal, this.ubicacion.localidad, arrcodigos.toString())
          .subscribe( cadenas  =>  {
                  this.loading = false;
                  cadenas.forEach(cadena => {
                    if (cadena.disponible) {
                      this.listaSucursales = this.listaSucursales.concat(this.agregarDatosSucursales(cadena.sucursales));
                    } else {
                      this.listaCadenasNoDisponibles.push(cadena);
                    }
                });
                  console.log('HTTP Response Comparador success');
                  this.loadColumns();
            }, err => {
                console.log('HTTP Error Comparador ', err);
                this.error = err;
                this.loading = false;
            }, () => console.log('HTTP Request Comparador completed')
          );
    }

    sucursalesEmpty() {
      if (this.listaSucursalesOrdenadas.length > 0) {
        return false;
        } else {
          return true;
      }
    }

    calcularCantidadColumnas() {

      let cant_columns = 1;
      if (this.listaSucursales !== undefined) {
        if (this.screenWidth < 1012) {
          cant_columns = 1;
        }
        if (this.screenWidth >= 1012) {
          cant_columns = 2;
        }
        if (this.screenWidth >= 1371) {
          cant_columns = 3;
        }

        if (this.screenWidth >= 1695) {
          cant_columns = 4;
        }

        if (this.listaSucursales.length < cant_columns ) {
          return this.listaSucursales.length;
        } else {
          return cant_columns;
        }
      }
    }

    cargarUbicacion() {
      const ub = sessionStorage.getItem('ubicacion');
      if (ub !== null) {
        this.ubicacion = JSON.parse(ub);
      }
    }

    @HostListener('window:resize', ['$event'])
      onResize(event) {
        this.screenWidth = window.innerWidth;
        if (this.listaProductos.length > 0) {
          this.loadColumns();
        }

    }

    constructor(
      private sCad: CadenasService,
      public  dialog: MatDialog,
      private data: DataSharingService
    ) { }

    ngOnInit() {
      this.screenWidth = window.innerWidth;
      this.cargarUbicacion();
      this.loadCadenas();
      this.cargarTablaPrecios();
    }

    ngOnDestroy() {
      this.suscripcionProductos.unsubscribe();
      this.suscripcionCadenasService.unsubscribe();
    }
  }
