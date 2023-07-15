import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BackendService} from './backend.service';

/**
 * Created by kamil on 03.05.17.
 */
@Injectable()
export class DatabaseService extends BackendService {

  constructor(protected http: HttpClient) {
    super(http);
  }

  protected get(endpoint: string) {
    const url = this.backendHref + endpoint;
    const res = this.http.get(url, {
      headers: this.prepareHeaders().append('Authorization', localStorage.getItem('jwt')),
      observe: 'response'
    });
    res.subscribe(this.handleError);
    return res;
  }

  protected post(endpoint: string, body) {
    const url = this.backendHref + endpoint;
    const res = this.http.post(url, body, {
      headers: this.prepareHeaders().append('Authorization', localStorage.getItem('jwt')),
      observe: 'response'
    });
    res.subscribe(this.handleError);
    return res;
  }

  protected put(endpoint: string, body?) {
    const url = this.backendHref + endpoint;
    const res = this.http.put(url, body, {
      headers: this.prepareHeaders().append('Authorization', localStorage.getItem('jwt')),
      observe: 'response'
    });
    res.subscribe(this.handleError);
    return res;
  }

  protected delete(endpoint: string) {
    const url = this.backendHref + endpoint;
    const res = this.http.delete(url, {
      headers: this.prepareHeaders().append('Authorization', localStorage.getItem('jwt')),
      observe: 'response'
    });
    res.subscribe(this.handleError);
    return res;
  }
}
