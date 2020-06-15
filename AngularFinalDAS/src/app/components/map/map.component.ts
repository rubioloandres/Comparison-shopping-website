import { Component, OnInit, AfterViewInit, Inject} from '@angular/core';
import { Coordenadas, MapData } from 'src/app/interfaces/ubicacion';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SucursalesComponent } from '../sucursales/sucursales.component';

declare var L: any;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  ubicacion: Coordenadas[];
  constructor(
    public dialogRef: MatDialogRef<SucursalesComponent>,
    // @Inject(MAT_DIALOG_DATA) public data: SucursalInfo) { }
    @Inject(MAT_DIALOG_DATA) public data: MapData
   ) { }

  loadUbicacion() {
    this.ubicacion = JSON.parse(sessionStorage.getItem('posicion'));
    console.log(this.ubicacion);
  }

  loadMap() {
/*
    let arrCoord;
    console.log(this.data);
    if (this.data !== undefined) {
      arrCoord = [this.data.latitud, this.data.longitud];
      console.log(arrCoord);
    } else {
      arrCoord = [this.ubicacion[0].latitud, this.ubicacion[0].longitud];
      console.log(arrCoord);
    }

    const map = new L.Map('map');
    const url = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
    const opt = {};
    const layer = new L.TileLayer(url, opt);
    map.addLayer(layer);
    console.log(arrCoord[0]);
    // const coord = new L.LatLng(60.1733244, 24.9410248);
    const coord = new L.LatLng(-31.416599, -64.187568);
    map.setView(coord, 9);

    */

    // TODO: Controlar carga de datos latitud longitud (invertidos)
    let arrCoord;
    console.log(this.data);
    if (this.data !== undefined) {
      arrCoord = [this.data.longitud, this.data.latitud];
      console.log(arrCoord);
    } else {
      arrCoord = [this.ubicacion[0].longitud, this.ubicacion[0].latitud];
      console.log(arrCoord);
    }
    // const map = L.map('map').setView(arrCoord, this.data.precision);
    const map = L.map ('map', {
      center: arrCoord,
      zoom: 13
     }).setView(arrCoord, this.data.precision);


    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
    const markerText = '<h2 style="color:DodgerBlue;">'
                     +   this.data.nombreUbicacion
                     + '</h2>'
                     + '<h4 style="color:grey;">'
                     +   this.data.direccion
                     + '</h4>';
    L.marker(arrCoord)
     .addTo(map).bindPopup(markerText)
     .openPopup();
  }

  ngOnInit() {
      this.loadUbicacion();
      this.loadMap();
  }
}
