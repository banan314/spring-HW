import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';

@Injectable()
export class LogInService {

  constructor(private http: HttpClient) { }

  login(email: string, password: string) {
    const loginRequest = JSON.stringify({email: email, password: password});
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Accept': 'application/json'});

    const result = this.http.post('/backend/login', loginRequest, {headers: headers, observe: 'response'});
    result.subscribe(response => this.mapResponse(response));
    return result;
  }

  private mapResponse(response: HttpResponse<Object>) {
    //TODO: on rejection
    if (response.status === 200) {
      localStorage.setItem('jwt', response.headers.get('x-auth-token'));
    }
  }

  isSignedIn(): boolean {
    return localStorage.getItem('jwt') !== null;
  }

  logout(): void {
    localStorage.removeItem('jwt');
  }
}
