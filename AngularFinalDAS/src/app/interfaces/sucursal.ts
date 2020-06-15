import { ProductoPrecio } from './producto';

export interface Sucursal {
  idSucursal: number;
  nombreSucursal: string;
  direccion: string;
  latitud: string;
  longitud: string;
  email: string;
  telefono: string;
  cuit: string;
  localidad: string;
  provincia: string;
  codigoEntidadFederal: string;
  idCadena: number;
  productos: ProductoPrecio[];
  cantidadDeProductosConPrecioMasBajo: number;
  mejorOpcion: boolean;
  total: number;
}

export interface TotalSucursal {
  numeroSucursal: number;
  totalPrecio: number;
}

// Se usa en dialog
export interface SucursalInfo {
  nombreCadena: string;
  imagenCadena: string;
  nombreSucursal: string;
  direccion: string;
  latitud: string;
  longitud: string;
}

export interface SucursalTablaPrecio {
  idSucursal: number;
  nombreSucursal: string;
  direccion: string;
  latitud: string;
  longitud: string;
  email: string;
  telefono: string;
  cuit: string;
  localidad: string;
  provincia: string;
  codigoEntidadFederal: string;
  idCadena: number;
  productos: ProductoPrecio[];
  cantidadDeProductosConPrecioMasBajo: number;
  mejorOpcion: boolean;
  total: number;
  imagenCadena: string;
  nombreCadena: string;
}
