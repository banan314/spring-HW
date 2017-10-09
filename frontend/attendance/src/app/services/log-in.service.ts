import { Injectable } from '@angular/core';
import {Http, Response, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/Rx";

@Injectable()
export class LogInService {

  constructor(private http: Http) { }

  login(email: string, password: string): Observable<Response> {
    let loginRequest = JSON.stringify({email: email, password: password});
    let headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json'});

    return this.http.post('/backend/login', loginRequest, {headers: headers})
      .do(response => this.mapResponse(response));
  }

  private mapResponse(response : Response) {
    //TODO: on rejection
    if (response.status === 200) {
      localStorage.setItem('jwt', response.headers.get('x-auth-token'));
    }
  }

  isSignedIn():boolean {
    return localStorage.getItem('jwt') !== null;
  }

  logout():void {
    localStorage.removeItem('jwt');
  }
}
