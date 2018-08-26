import { Injectable } from '@angular/core';
import {UserDTO} from '../dto/user-dto';
import {DatabaseService} from './database.service';

// @Injectable({
//   providedIn: 'root'
// })
export class RegisterService extends DatabaseService {

  private registerHref = '/register';

  public registerUser(userDTO: UserDTO) {
    return this.http.post(this.backendHref + this.registerHref, JSON.stringify(userDTO),
       { headers: this.prepareHeaders(), observe: 'response' }  );
  }
}
