import { Component, Injectable } from '@angular/core';
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {User} from "./model/user";
import {UserService} from "./services/user.service";
import {ActivityService} from "./services/activity.service";
import {Activity} from "./model/activity";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UserService, ActivityService],
})
export class AppComponent {
  title = 'Attendance manager';
  greetings :string = "Sprawdź swoją obecność i dostępne zajęcia. Dodaj zajęcia lub użytkowników.";

  users: User[];
  user: User = new User;

  addUser() {
    console.log("adduser");
    this.userService.addUser(this.user);
  }

  deleteUser(user: User) {
    console.log('delete user ' + user.username);
    this.userService.deleteUser(user.id);
  }

  constructor(private http: Http, private userService: UserService, private activityService: ActivityService) {
    userService.getUsers().then(res => {this.users = res; console.log(this.users.length)});
  }
}
