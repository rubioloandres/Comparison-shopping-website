import { Component, OnInit, OnDestroy, ChangeDetectorRef, Inject, LOCALE_ID } from '@angular/core';
import { LocalidadesService } from './services/indec/localidades.service';
import { CategoriasService } from './services/indec/categorias.service';
import { ProductosService } from './services/indec/productos.service';
import { ProvinciasService } from './services/indec/provincias.service';
import { CadenasService } from './services/indec/cadenas.service';
import { MenuService } from './services/indec/menu.service';
import { Categoria } from './interfaces/categoria';
import { Subscription, Observable } from 'rxjs';
import { DataSharingService } from './services/datasharing.service';
import { MatDialog } from '@angular/material';
import { Ubicacion, Coordenadas } from './interfaces/ubicacion';
import { Producto } from './interfaces/producto';
import { FormControl } from '@angular/forms';
import { Provincia } from './interfaces/provincia';
import { Idioma } from './interfaces/idioma';
import { Cadena } from './interfaces/cadena';
import { DialogLocationComponent } from './components/location/location.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'Comparador de precios';
  error: any;
  nombreComponente = 'NavBarComponent';
  listaCadenas: Cadena[] = new Array();
  listaProvincias: Provincia[] = new Array();
  listaCategorias: Categoria[] = new Array();
  listaIdiomas: Idioma[] = [
    { nombre: 'ES', imagen: './../../../assets/img/lang_esp_icon.png' , puerto: 4200 },
    { nombre: 'EN', imagen: './../../../assets/img/lang_eng_icon.png' , puerto: 44316 }
  ];
  idiomaActual: Idioma;
  searchInput = '';
  listaubicaciones: Coordenadas[] = new Array();
  ubicacion: Ubicacion;
  ubicacionActual: Ubicacion;
  listaProductos: Producto[] = new Array();
  formBusProd = new FormControl(this.searchInput);
  private suscripcionformBusProd: Subscription;
  filteredProds: Observable<Producto[]>;

  selected = new FormControl(0);
  seccionActiva = 0;


  constructor(
    private sCat: CategoriasService,
    private sProd: ProductosService,
    private sProv: ProvinciasService,
    private sLoc: LocalidadesService,
    private sCad: CadenasService,
    private sMen: MenuService,
    private data: DataSharingService,
    public dialog: MatDialog,
    private cdRef: ChangeDetectorRef,
    @Inject(LOCALE_ID) locale: string
  ) {
        if (locale === 'en') {
          this.idiomaActual = this.listaIdiomas.find(i => i.nombre === 'EN');
        } else {
          this.idiomaActual = this.listaIdiomas.find(i => i.nombre === 'ES');
        }
   }

  suscripcionCadenasService: Subscription;
  suscripcionProductoService: Subscription;
  suscripcionCategoriasService: Subscription;
  suscripcionProvinciasService: Subscription;
  suscripcionLocalidadesService: Subscription;
  suscripcionMenuService: Subscription;

  buscarProductos() {
    this.suscripcionProductoService = this.sProd.buscarProductos({ componente: 'SearchbarComponent' }).subscribe(
      prods => {
        // console.log('HTTP Response Productos', prods);
        sessionStorage.setItem('productos', JSON.stringify(prods));
      },
      err => {
        // console.log('HTTP Error Productos', err);
        sessionStorage.setItem('productos', '[]');
        this.error = err;
      },
      () => { /*console.log('HTTP Request Productos completed')*/ }
    );
  }

  getCategorias() {
    this.suscripcionCategoriasService = this.sCat.getCategorias().subscribe(
      cats => {
        // console.log('HTTP Response Categorias', cats);
        sessionStorage.setItem('categorias', JSON.stringify(cats));
      },
      err => {
        // console.log('HTTP Error Categorias', err);
        sessionStorage.setItem('categorias', '[]');
        this.error = err;
      },
      () =>  { /*console.log('HTTP Request Categorias completed')*/ }
    );
  }

  getProvincias() {
    this.suscripcionProvinciasService = this.sProv.getProvincias().subscribe(
      provs => {
        // console.log('HTTP Response Provincias', provs);
        sessionStorage.setItem('provincias', JSON.stringify(provs));
      },
      err => {
        // console.log('HTTP Error Provincias', err);
        sessionStorage.setItem('provincias', '[]');
        this.error = err;
      },
      () =>  { /*console.log('HTTP Request Provincias completed')*/ }
    );
  }

  getLocalidades() {
    this.suscripcionLocalidadesService = this.sLoc.getLocalidades().subscribe(
      locs => {
        // console.log('HTTP Response Localidades', locs);
        sessionStorage.setItem('localidades', JSON.stringify(locs));
      },
      err => {
        //  console.log('HTTP Error Localidades', err);
        sessionStorage.setItem('localidades', '[]');
        this.error = err;
      },
      () => { /*console.log('HTTP Request Localidades completed')*/ }
    );
  }

  getCadenas() {
    this.suscripcionCadenasService = this.sCad.getCadenas().subscribe(
      cads => {
        // console.log('HTTP Response Cadenas', cads);
        sessionStorage.setItem('cadenas', JSON.stringify(cads));
      },
      err => {
        // console.log('HTTP Error Cadenas', err);
        sessionStorage.setItem('cadenas', '[]');
        this.error = err;
      },
      () => { /*console.log('HTTP Request Cadenas completed')*/ }
    );
  }

  getMenu() {
    this.suscripcionMenuService = this.sMen.getMenu().subscribe(
      menus => {
        // console.log('HTTP Response Menu', menus);
        sessionStorage.setItem('menu', JSON.stringify(menus));
      },
      err => {
        // console.log('HTTP Error Menu', err);
        sessionStorage.setItem('menu', '[]');
        this.error = err;
      },
      () => { /* console.log('HTTP Request Menu completed')*/ }
    );
  }

  inicializarCarrito(): void {
    const carr = sessionStorage.getItem('carrito');
    if ( carr !== null && carr !== '') {
      sessionStorage.setItem('carrito', carr);
    } else {
      sessionStorage.setItem('carrito', '[]');
    }
  }


  loadCadenas() {
    const cadenas = sessionStorage.getItem('cadenas');
    if (cadenas !== null) {
      if (cadenas.length > 0) {
        this.listaCadenas = JSON.parse(cadenas);
        console.log('Cadenas cargadas exitosamente');
      }
    } else {
      console.log('Cadenas no cargadas exitosamente');
    }
  }

  loadCategories() {
    const categorias = sessionStorage.getItem('categorias');
    if (categorias !== null) {
      const lcat: Categoria[] = JSON.parse(categorias);
      if (lcat.length > 0) {
        lcat.forEach(cat => {
          this.listaCategorias.push(cat);
        });
        console.log('Categorias cargadas exitosamente');
      }
    } else {
      console.log('Categorias no cargadas exitosamente');
    }
  }

  loadProvinces() {
    const provincias = sessionStorage.getItem('provincias');
    if (provincias !== null) {
      const lprov: Provincia[] = JSON.parse(provincias);
      if (lprov.length > 0) {
        this.listaProvincias = lprov;
        console.log('Provincias cargadas exitosamente');
      }
    } else {
      console.log('Provincias no cargadas exitosamente');
    }
  }

  getProvincia(codEntFed: string) {
    return this.listaProvincias.find(prov => prov.codigoEntidadFederal === codEntFed);
  }


  searchProductsPorPalabraClave(): void {
    this.suscripcionformBusProd = this.formBusProd.valueChanges.subscribe(palabra => {
      if (palabra.length > 1) {
        this.data.changeCriterioBusquedaProducto({ palabraclave: palabra, componente: this.nombreComponente });
      } else {
        this.data.changeCriterioBusquedaProducto({ componente: this.nombreComponente });
      }
    });
  }

  onClickSearchProductsPorPalabraClave(): void {
      if (this.formBusProd.value.length > 1) {
        this.data.changeCriterioBusquedaProducto({ palabraclave: this.searchInput, componente: this.nombreComponente });
      }
  }

  searchAllProducts() {
    this.data.changeCriterioBusquedaProducto({ componente: this.nombreComponente });
  }

  hayUbicacion(): boolean {
    if (sessionStorage.getItem('ubicacion') !== null) {
      return true;
    } else {
      return false;
    }
  }

  loadUbicacion() {
    const ubicacion = sessionStorage.getItem('ubicacion');
    if (ubicacion !== null) {
      this.ubicacion = JSON.parse(ubicacion);
    }
  }

  registrarUbicacion() {
    const dialogRef = this.dialog.open(DialogLocationComponent, {
      width: '500px'
    });
  }

  setIdioma(idioma: Idioma) {
    let url = '';
    if (idioma.nombre === 'ES') {
      url = 'http://localhost:' + idioma.puerto;
      return url;
    }

    if (idioma.nombre === 'EN') {
      url = 'http://localhost:' + idioma.puerto;
      return url;
    }
  }

  ngOnInit(): void {
    this.inicializarCarrito();
    this.buscarProductos();
    this.getCategorias();
    this.getProvincias();
    this.getLocalidades();
    this.getCadenas();
    this.getMenu();

    this.loadCategories();
    this.loadProvinces();
    this.loadCadenas();
    this.searchProductsPorPalabraClave();

    this.data.currentUbicacion.subscribe(ub => {
      if (ub.localidad !== 'default') {
        this.ubicacionActual = ub;
      }
    });
  }

  ngOnDestroy(): void {
    this.suscripcionCadenasService.unsubscribe();
    this.suscripcionProductoService.unsubscribe();
    this.suscripcionCategoriasService.unsubscribe();
    this.suscripcionProvinciasService.unsubscribe();
    this.suscripcionLocalidadesService.unsubscribe();
    this.suscripcionformBusProd.unsubscribe();
  }


  ngAfterViewChecked() {
    this.cdRef.detectChanges();
  }



}
