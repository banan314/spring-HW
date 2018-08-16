import { HttpClient, HttpHeaders } from '@angular/common/http';

/**
 * Created by kamil on 03.05.17.
 */
export class DatabaseService {
  protected backendHref = 'backend';

  constructor(protected http: HttpClient) {

  }

  protected handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  protected prepareHeaders() {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'x-auth-token': localStorage.getItem('jwt')
    });
  }
}
