import {Component, Injectable} from '@angular/core';
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
  greetings: string = "Sprawdź swoją obecność i dostępne zajęcia. Dodaj zajęcia lub użytkowników.";

  constructor() {

  }
}
