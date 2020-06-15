import { Respuesta } from '../interfaces/comparador';

export class ResolvedRespuestaComparador {
  constructor(
    public respuesta: Respuesta,
    public error: any = null
  ) { }
}
