import {DatabaseService} from './database.service';
import {User} from '../model/user';
import {Injectable} from '@angular/core';

@Injectable()
export class UserService extends DatabaseService {

  private usersHref = '/users';

  getUsers() {
    return this.http.get(this.backendHref + this.usersHref);
  }

  getById(id: number) {
    return this.get(this.composeIdUrl(id));
  }

  addUser(user: User) {
    return this.post(this.usersHref, {username: user.username});
  }

  deleteUser(id: number) {
    return this.delete(this.composeIdUrl(id));
  }

  updateUser(id: number, user: User) {
    return this.put(this.composeIdUrl(id), user);
  }

  assignActivity(activityId: number, userId: number) {
    return this.put(this.composeIdUrl(userId) + '/activities/' + activityId.toString());
  }

  private composeIdUrl(id: number): string {
    return this.usersHref + '/' + id.toString();
  }
}
