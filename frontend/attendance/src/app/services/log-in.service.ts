import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {CookieService} from 'ngx-cookie-service';

@Injectable()
export class LogInService {

  private _url = '/login';
  private backendHref = 'http://localhost:' + '8080';
  private _isLoggedIn = false;

  constructor(private http: HttpClient, private cookie: CookieService) { }

  login(username: string, password: string) {
    // const parameters: string = ['username=' + username, 'password=' + password].join('&');
    // const headers = new HttpHeaders().append('Content-Type', 'application/x-www-form-urlencoded');
    //
    // const result = this.http.post(this.backendHref + this._url, parameters, {headers: headers, observe: 'response'});
    // result.subscribe(response => this.mapResponse(response), (error) => {
    //   console.log(error);
    // });
    // return result;

    const headers = new HttpHeaders({
      authorization : 'Basic ' + btoa(username + ':' + password)
    });

    const response = this.http.get(this.backendHref + this._url, {headers: headers});

    response.subscribe((res: Response) => {
      const status = res.status;
      if (status === 200) {
        this._isLoggedIn = true;
      } else {
        this._isLoggedIn = false;
      }
    });

    return response;
  }

  private mapResponse(response: HttpResponse<Object>) {
      console.log(response.headers);
  }

  isLoggedIn(): boolean {
    return this._isLoggedIn;
  }

  logout(): void {
    this.cookie.delete('JSESSIONID');
  }
}
