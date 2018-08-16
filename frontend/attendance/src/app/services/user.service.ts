import {Injectable} from '@angular/core';
import {DatabaseService} from './database.service';
import {User} from '../model/user';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService extends DatabaseService {

  private usersHref = '/users';

  getUsers() {
    return this.http.get(this.backendHref + this.usersHref);
  }

  getById(id: number) {
    return this.http.get(this.composeIdUrl(id));
  }

  addUser(user: User, callback: () => void) {
    console.log('add ' + user.username);
    console.log('href = ' + this.backendHref + this.usersHref);
    return this.http.post(this.backendHref + this.usersHref,
      JSON.stringify({username: user.username}),
      {headers: this.prepareHeaders()});
  }

  deleteUser(id: number, callback?: () => void) {
    return this.http.delete(this.composeIdUrl(id), null);
  }

  updateUser(id: number, user: User) {
    return this.http.put(this.composeIdUrl(id), user);
  }

  assignActivity(activityId: number, userId: number) {
    return this.http.put(this.composeIdUrl(userId) + '/activities/' + activityId.toString(), null);
  }

  private composeIdUrl(id: number): string {
    return this.backendHref + this.usersHref + '/' + id.toString();
  }
}
