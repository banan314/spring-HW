import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

/**
 * Created by kamil on 03.05.17.
 */
@Injectable()
export class DatabaseService {
  protected backendHref = 'http://localhost:8080';
  // protected backendHref = '/backend';

  constructor(protected http: HttpClient) {

  }

  protected get(endpoint: string, subscribe: (() => void)) {
    const url = this.backendHref + endpoint;
    this.http.get(url, {headers: this.prepareHeaders(), withCredentials: true, observe: 'response'})
      .subscribe(subscribe, this.handleError);
  }

  protected post(endpoint: string, body: string, subscribe: (() => void)) {
    const url = this.backendHref + endpoint;
    this.http.post(url, body, {headers: this.prepareHeaders(), withCredentials: true, observe: 'response'})
      .subscribe(subscribe, this.handleError);
  }

  protected put(endpoint: string, body: string, subscribe: (() => void)) {
    const url = this.backendHref + endpoint;
    this.http.put(url, body, {headers: this.prepareHeaders(), withCredentials: true, observe: 'response'})
      .subscribe(subscribe, this.handleError);
  }

  protected delete(endpoint: string, body: string, subscribe: (() => void)) {
    const url = this.backendHref + endpoint;
    this.http.delete(url, {headers: this.prepareHeaders(), withCredentials: true, observe: 'response'})
      .subscribe(subscribe, this.handleError);

  }

  protected handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  protected prepareHeaders() {
    return new HttpHeaders()
      .append('Content-Type', 'application/json')
      .append('Accept', 'application/json');
  }
}
