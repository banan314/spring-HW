/**
 * Created by kamil on 01.05.17.
 */
import {Component, OnInit} from '@angular/core';
import {Activity} from "../model/activity";
import {User} from "../model/user";
import {UserService} from "../services/user.service";

@Component({
  moduleId: module.id,
  selector: 'attendance-list',
  templateUrl: 'html/attendance-list.component.html',
})

export class AttendanceListComponent implements OnInit {
  users: User[];
  user: User = new User;

  addUser() {
    console.log("adduser");
    this.userService.addUser(this.user,
      () => {
        this.getUsers();
      }
    );
  }

  getUsers() {
    this.userService.getUsers().then(res => {
      this.users = res;
      console.log("users length = " + this.users.length)
    });
  }

  deleteUser(user: User) {
    console.log('delete user ' + user.username);
    this.userService.deleteUser(user.id, () => this.getUsers());
  }

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.getUsers();
  }
}
