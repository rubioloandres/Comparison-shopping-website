import { Sucursal } from './sucursal';

export interface Cadena {
  idCadena: number;
  nombreCadena: string;
  imagenCadena: string;
}

export interface CadenaSucursal {
  idCadena: number;
  nombreCadena: string;
  sucursales: Sucursal [];
  disponible: boolean;
}
