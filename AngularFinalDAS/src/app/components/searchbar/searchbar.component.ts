import { Component, OnInit } from '@angular/core';
import { DataSharingService } from 'src/app/services/datasharing.service';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent {

  nombreComponente: string = 'SearchbarComponent';
  searchInput = '';

  customOptions: any = {
    loop: true,
    mouseDrag: false,
    touchDrag: false,
    pullDrag: false,
    dots: false,
    autoplay: true,
    navSpeed: 600,
    navText: [''],
    responsive: {
      0: {
        items: 1
      } ,
      200: {
        items: 1
      },
      400: {
        items: 1
      },
      600: {
        items: 1
      }
    },
    nav: false
  };

  searchProducts() {
    this.data.changeCriterioBusquedaProducto({palabraclave : this.searchInput,componente:this.nombreComponente});
  }

  constructor(
    private data: DataSharingService
  ) { }

  ngOnInit() {
  }
  
}
