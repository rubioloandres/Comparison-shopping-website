import { ProductoIngrediente } from './producto';

export interface Menu {
  idMenu: number;
  nombreMenu: string;
  dia: string;
  platos: Plato [];
}

export interface Plato {
  idPlato: number;
  nombrePlato: string;
  preparacion: string;
  imagenPlato: string;
  idMenu: number;
  ingredientes: ProductoIngrediente [];
}
