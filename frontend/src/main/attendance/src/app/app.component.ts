import { Component, Injectable } from '@angular/core';
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {User} from "./model/user";
import {UserService} from "./services/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UserService],
})
export class AppComponent {
  title = 'Attendance manager';
  greetings :string = "";

  users: User[];

  getGreeting() {
    let result :Promise<User> =
    this.userService.getById(4);
    return result;
  }

  constructor(private http: Http, private userService: UserService) {
    userService.getUsers().then(res => {this.users = res; console.log("users number=" + this.users.length);});

    this.getGreeting().then(value => this.greetings = value.sex.toString());
  }
}
