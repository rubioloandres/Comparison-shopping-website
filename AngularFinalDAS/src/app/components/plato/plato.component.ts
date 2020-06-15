import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { Plato } from 'src/app/interfaces/menu';
import { DialogLocationComponent } from '../location/location.component';
import { DataSharingService } from 'src/app/services/datasharing.service';
import { Ubicacion } from 'src/app/interfaces/ubicacion';

@Component({
  selector: 'app-dialog-plato',
  templateUrl: './plato.component.html',
  styleUrls: ['./plato.component.css']
})
export class DialogPlatoComponent {

  constructor(
    public dialogRef: MatDialogRef<DialogPlatoComponent>,
    public dialog: MatDialog,
    private ds: DataSharingService,
    @Inject(MAT_DIALOG_DATA) public data: Plato) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ubicacionCargada() {
    const ub = sessionStorage.getItem('ubicacion');
    if (ub !== null) {
      if (sessionStorage.getItem('ubicacion') !== '') {
          return true;
      }
    }
    return false;
  }

  registrarUbicacion() {
    const dialogRef = this.dialog.open(DialogLocationComponent, {
      width: '500px'
    });
  }

  enviarPlato(idPlato: number) {
    this.ds.changePlato(idPlato);
  }

}
