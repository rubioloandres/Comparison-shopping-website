import { Producto } from 'src/app/interfaces/producto';

export class ResolvedProductos {
  constructor(
    public categorias: Producto[],
    public error: any = null
    ) {}
}
