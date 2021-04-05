import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class BackendService {
  protected backendHref = 'http://localhost:8080';

  constructor(protected http: HttpClient) { }

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
