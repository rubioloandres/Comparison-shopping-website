export interface Coordenadas {
  latitud: number;
  longitud: number;
  precision: number;
}

export interface Ubicacion {
  codigoEntidadFederal: string;
  localidad: string;
  provincia: string;
}

export interface MapData {
  latitud: number;
  longitud: number;
  precision: number;
  nombreUbicacion: string;
  direccion: string;
}


