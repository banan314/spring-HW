/**
 * Created by kamil on 01.05.17.
 */
import {Inject, Injectable} from '@angular/core';
import {User} from "../model/user";

import {Http} from "@angular/http";
import {DatabaseService} from "./database.service";

/*TODO: add RxJS library*/

@Injectable()
export class UserService extends DatabaseService{
  private usersHref = '/users';

  /*TODO:
  * - update
  * - assign activity
  * */

  getUsers() :Promise<User[]> {
    return this.http.get(this.backendHref + this.usersHref)
      .toPromise()
      .then(response => response.json() as User[])
      .catch(this.handleError);
  }

  getById(id: number) :Promise<User> {
    let url = this.backendHref + this.usersHref + '/' + id.toString();
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as User)
      .catch(this.handleError);
  }

  addUser(user: User): void {
    console.log("add " + user.username);
    console.log("href = " + this.backendHref + this.usersHref)
    this.http.post(this.backendHref + this.usersHref, JSON.stringify({username: user.username}), {headers: this.headers})
      .toPromise()
      .then(res => res.text())
      .catch(this.handleError);
  }

  deleteUser(id: number) {
    this.http.delete(this.composeIdUrl(id), null)
      .toPromise()
      .then(res => res.text())
      .catch(this.handleError);
  }

  updateUser(id: number, user: User) {
    this.http.put(this.composeIdUrl(id), user)
       .toPromise()
       .then(response => response.text())
       .catch(this.handleError);
  }

  assignActivity(activityId: number, userId: number): void {
    this.http.put(this.composeIdUrl(userId) + "/activities/" + activityId.toString(), null)
      .toPromise()
      .then(response => response.text())
      .catch(this.handleError);
  }

  private composeIdUrl(id: number): string {
    return this.backendHref + this.usersHref + '/' + id.toString();
  }

  constructor(private http: Http) {
    super();
  }
}
