import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SucursalInfo } from 'src/app/interfaces/sucursal';

@Component({
  selector: 'app-dialog-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class DialogInfoSucursalComponent {

  constructor(
    public dialogRef: MatDialogRef<DialogInfoSucursalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: SucursalInfo) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
