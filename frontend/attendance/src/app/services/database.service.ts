import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Injectable} from '@angular/core';

/**
 * Created by kamil on 03.05.17.
 */
@Injectable()
export class DatabaseService {
  protected backendHref = 'http://localhost:8080';
  // protected backendHref = '/backend';

  constructor(protected http: HttpClient) {

  }

  protected handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  protected prepareHeaders() {
    let headers = new HttpHeaders()
      .append('Content-Type', 'application/json')
      .append('Accept', 'application/json');
    if (null != localStorage.getItem('jwt')) {
      headers = headers.append('x-auth-token', localStorage.getItem('jwt'));
    }
    return headers;
  }
}
