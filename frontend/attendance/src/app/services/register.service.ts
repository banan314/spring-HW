import {Injectable} from '@angular/core';
import {UserDTO} from '../dto/user-dto';
import {BackendService} from './backend.service';

@Injectable()
export class RegisterService extends BackendService {

  private _url = this.backendHref + '/auth/register';

  public registerUser(userDTO: UserDTO) {
    return this.http.post(this._url, JSON.stringify(userDTO), {
      headers: this.prepareHeaders(),
      observe: 'response'
    });
  }
}
