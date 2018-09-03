import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {RegisterService} from 'src/app/services/register.service';
import {UserDTO} from '../dto/user-dto';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string;
  confirmPassword: string;

  constructor(private registerService: RegisterService) { }

  ngOnInit() {
  }

  registerAccount(): void {
    const userDTO: UserDTO = {
      firstName: this.firstName,
      lastName: this.lastName,
      username: this.username,
      email: this.email,
      password: this.password,
      matchingPassword: this.confirmPassword
    };

    this.registerService.registerUser(userDTO)
      .subscribe(resp => {
        console.log('response from register: ', resp);
      }, error => {
        console.log(error);
      });
  }

}
