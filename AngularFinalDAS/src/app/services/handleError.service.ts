import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';

export class ErrorManager {

  constructor() {}

  public handleHTTPError(errorResponse: HttpErrorResponse) {
    if (errorResponse.error instanceof ErrorEvent) {
    //  console.log('Client Side Error: ', errorResponse.message);
    } else {
     // console.log('Server Side Error: ', errorResponse);
    }
    return throwError('There is a problem with the service. We are notified & working on it. Please tyy again later.');
  }
}
