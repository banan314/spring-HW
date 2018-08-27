import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';

@Injectable()
export class LogInService {

  constructor(private http: HttpClient) { }

  private _url = '/login';
  private backendHref = 'http://localhost:8080';

  login(username: string, password: string) {
    const parameters: string = ['username=' + username, 'password=' + password].join('&');
    const headers = new HttpHeaders().append('Content-Type', 'application/x-www-form-urlencoded');

    const result = this.http.post(this.backendHref + this._url, parameters, {headers: headers, observe: 'response'});
    result.subscribe(response => this.mapResponse(response));
    return result;
  }

  private mapResponse(response: HttpResponse<Object>) {
    if (response.status === 200) {
      localStorage.setItem('session_id', response.headers.get('x-auth-token'));
    }
  }

  isSignedIn(): boolean {
    return localStorage.getItem('session_id') !== null;
  }

  getSessionId() {
    return localStorage.getItem('session_id');
  }

  logout(): void {
    localStorage.removeItem('session_id');
  }
}
