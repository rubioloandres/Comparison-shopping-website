import { Component, OnInit } from '@angular/core';
import { SucursalesService } from 'src/app/services/indec/sucursales.service';
import { Ubicacion } from 'src/app/interfaces/ubicacion';
import { Sucursal } from 'src/app/interfaces/sucursal';
import { Cadena, CadenaSucursal } from 'src/app/interfaces/cadena';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { MapComponent } from '../map/map.component';

@Component({
  selector: 'app-sucursales',
  templateUrl: './sucursales.component.html',
  styleUrls: ['./sucursales.component.css']
})
export class SucursalesComponent implements OnInit {

  listaSucursales: Sucursal[] = new Array();
  listaCadenas: Cadena [] = new Array();
  error: string;
  loading = true;
  listaCadenasNoDisponibles: CadenaSucursal [] = new Array();

  updateSucursales() {

    this.listaCadenasNoDisponibles  = new Array();

    const ubicacion: Ubicacion = JSON.parse(sessionStorage.getItem('ubicacion'));
    this.sSuc.getSucursales(ubicacion.codigoEntidadFederal, ubicacion.localidad)
    .subscribe( cadenas  =>  {
            this.loading = false;
            cadenas.forEach(cadena => {
              if (cadena.disponible) {
                this.listaSucursales = this.listaSucursales.concat(cadena.sucursales);
              } else {
                this.listaCadenasNoDisponibles.push(cadena);
              }
          });
            console.log('HTTP Response Sucursales success');
            console.log(this.listaSucursales);
      }, err => {
          console.log('HTTP Error Sucursales ', err);
          this.error = err;
      }, () => console.log('HTTP Request Sucursales completed')
    );
  }

  getImagenCadena(idCad: number) {
    const cads = sessionStorage.getItem('cadenas');
    if (cads !== null) {
      const lcad: Cadena[] = JSON.parse(cads);
      return lcad.find(c => c.idCadena === idCad).imagenCadena;
    }
  }

  getCadena(id: number) {
    return this.listaCadenas.find(cad => cad.idCadena === id);
  }

  loadCadenas() {
    this.listaCadenas = JSON.parse(sessionStorage.getItem('cadenas'));
  }

  showMapa(lat: number, lng: number, nombreUbic: string, ubic: string) {
    const dialogRef = this.dialog.open(MapComponent, {
      width: '500px',
      height: '500px',
      data: { latitud: lat, longitud: lng, precision: 100, nombreUbicacion: nombreUbic, direccion: ubic}
    });

  }

  constructor(
    private sSuc: SucursalesService,
    public dialog: MatDialog
    ) { }

  ngOnInit() {
    this.loadCadenas();
    this.updateSucursales();
  }

}
