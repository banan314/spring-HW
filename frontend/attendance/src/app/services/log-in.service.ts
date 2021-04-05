import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Token} from '../model/Token';
import {BackendService} from './backend.service';

@Injectable({
  providedIn: 'root'
})
export class LogInService extends BackendService {

  private _url = '/auth/login';

  constructor(protected http: HttpClient) {
    super(http);
  }

  login(username: string, password: string) {
    const headers = new HttpHeaders().append('Content-Type', 'application/json');

    const res = this.http.post(this.backendHref + this._url, {
      username: username,
      password: password
    }, {
      headers: headers,
      observe: 'response'
    });
    res.subscribe(this.mapResponse, this.handleError);

    return res;
  }

  private mapResponse(res: HttpResponse<Object>) {
    const status = res.status;
    if (status === 200) {
      const token = (res.body as Token).token;
      localStorage.setItem('jwt', token);
    }
  }

  isLoggedIn(): Promise<boolean> {
    const token = localStorage.getItem('jwt');
    return new Promise<boolean>(resolve => {
      if (token) {
        const payload = JSON.parse(atob(token.split('.')[1]));
        resolve(payload.exp > (Date.now() / 1000));
      } else {
        resolve(false);
      }
    });
  }
}

