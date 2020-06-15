import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { Dia } from 'src/app/interfaces/dia';
import { Menu, Plato } from 'src/app/interfaces/menu';
import { DataSharingService } from 'src/app/services/datasharing.service';
import { Ubicacion } from 'src/app/interfaces/ubicacion';
import { MatDialog } from '@angular/material';
import { DialogLocationComponent } from '../location/location.component';
import { DialogPlatoComponent } from '../plato/plato.component';

@Component({
  selector: 'app-health',
  templateUrl: './health.component.html',
  styleUrls: ['./health.component.css']
})
export class HealthComponent implements OnInit {

  diasDeSemana: Dia [] = new Array();
  diaActual: Dia;
  menuSemanal: Menu [] = new Array();
  menuDiario: Menu;
  panelIngOpenState = false;
  panelPrepOpenState = false;
  ubicacion: Ubicacion;
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

  cargarSemana() {
    const startOfWeek = moment().startOf('isoWeek');
    const endOfWeek = moment().endOf('isoWeek');

    let dia: Dia;
    const days = [];
    let day = startOfWeek;
    const currentDay = moment().locale('es').format('dddd');
    let nombreDia: string;
    while (day <= endOfWeek) {
        days.push(day.toDate());
        nombreDia = day.locale('es').format('dddd').toUpperCase();
        dia = {
            nombre: nombreDia
          , numero: day.format('DD')
          , mes: day.format('MMMM').toUpperCase()
          , actual: (currentDay === day.locale('es').format('dddd') ? ( this.cargarMenuDiario(nombreDia) ) : false)
        };
        day = day.clone().add(1, 'd');
        this.diasDeSemana.push(dia);
    }
  }

  cargarMenuSemanal() {
    const lmen = sessionStorage.getItem('menu');
    if (lmen != null || ( lmen.length > 0)) {
      this.menuSemanal = JSON.parse(lmen);
    } else {
      this.menuSemanal = [];
    }
  }

  getMenuDiario(dia: string) {
    return this.menuSemanal.find( menu => menu.dia === dia);
  }

  actualizarDia() {
    this.diaActual = {
      nombre: moment().locale('es').format('dddd'),
      numero: moment().format('DD'),
      mes: moment().format('MMMM').toUpperCase(),
      actual: true
    };
  }

  cargarMenuDiario(dia: string) {
    this.menuDiario = this.menuSemanal.find( menu => menu.dia === dia);
    console.log(this.menuDiario);
    return true;
  }

  enviarPlato(idPlato: number) {
    this.data.changePlato(idPlato);
  }

  ubicacionCargada() {
    if (sessionStorage.getItem('ubicacion') === '') {
      return false;
    } else {
      return true;
    }
  }

  registrarUbicacion() {
    const dialogRef = this.dialog.open(DialogLocationComponent, {
      width: '500px'
    });
  }


  openDialogPlato(plato: Plato): void {
    const dialogRef = this.dialog.open(DialogPlatoComponent, {
      width: '800px',
      height: '650px',
      data: plato
    });
  }

  constructor(
    private data: DataSharingService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.cargarMenuSemanal();
    this.cargarSemana();
    this.actualizarDia();
  }
}
