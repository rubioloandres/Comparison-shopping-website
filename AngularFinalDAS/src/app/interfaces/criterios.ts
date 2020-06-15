import { Producto } from './producto';
export interface CriterioBusquedaProducto{
  idCategoria?: number;
  marcas?: string[];
  palabraclave?: string;
  componente:string; //Nombre del componente que cambia el criterio de busqueda.
}

export interface CatalogoActualizado {
  productos:Producto[];
  componente:string;
}