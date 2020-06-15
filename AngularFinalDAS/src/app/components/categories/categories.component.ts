import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { DataSharingService } from '../../services/datasharing.service';
import { Categoria } from './../../interfaces/categoria';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { CategoriasService } from 'src/app/services/indec/categorias.service';
import { FlexLayoutModule } from '@angular/flex-layout';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit, OnDestroy{


  nombreComponente = 'CategoriesComponent';
  listaCategorias: Categoria [] = new Array();
  suscripcionCategoriasService: Subscription;


  buscarProductos(idCat: number) {
    this.data.changeCriterioBusquedaProducto({idCategoria: idCat , componente: this.nombreComponente});
  }



  obtenerCategorias() {
    this.suscripcionCategoriasService = this.sCat.getCategorias().subscribe (
      cats => {
        console.log('HTTP Response Categorias');
        sessionStorage.setItem('categorias', JSON.stringify(cats));
        this.listaCategorias = cats;
      },
      err => {
        console.log('HTTP Error Categorias');
      },
      () => console.log('HTTP Request Categorias completed')
    );
 }

  constructor(private data: DataSharingService,private sCat:  CategoriasService) { }

  ngOnInit() {
    this.obtenerCategorias();
  }

  ngOnDestroy(): void {
    this.suscripcionCategoriasService.unsubscribe();
  }
}


