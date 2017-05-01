/**
 * Created by kamil on 01.05.17.
 */
import {Injectable} from '@angular/core';
import {User} from "../model/user";

import {Http} from "@angular/http";

@Injectable()
export class UserService {
  private backendHref= "backend";
  private usersHref = '/users';

  getUsers() :Promise<User[]> {
    return this.http.get(this.backendHref + '/users')
      .toPromise()
      .then(response => response.json() as User[])
      .catch(this.handleError);
  }

  getById(id: number) :Promise<User> {
    let url = this.backendHref + '/users/' + id.toString();
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as User)
      .catch(this.handleError);
  }
  constructor(private http: Http) {
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
