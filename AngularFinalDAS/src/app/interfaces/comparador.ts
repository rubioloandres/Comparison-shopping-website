import { CadenaSucursal } from './cadena';

export interface EstadoRespuesta {
  codigo: number;
  mensaje: string;
}

export interface Respuesta {
  codigo: number;
  mensaje: string;
  cadenas: CadenaSucursal [];
}
