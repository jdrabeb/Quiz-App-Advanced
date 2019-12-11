import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        req = req.clone({
        setHeaders: {
            'Authorization': `${localStorage.getItem('token')}`,
            'Content-Type' : 'application/json',
      },
    });

    return next.handle(req);
  }
}

