import { Categoria } from 'src/app/interfaces/categoria';

export class ResolvedCategorias {
  constructor(
    public categorias: Categoria[],
    public error: any = null
    ) {}
}
