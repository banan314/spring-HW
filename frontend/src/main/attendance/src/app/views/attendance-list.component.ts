/**
 * Created by kamil on 01.05.17.
 */
import {Component, OnInit} from '@angular/core';
import {Activity} from "../model/activity";
import {User} from "../model/user";
import {UserService} from "../services/user.service";
import {isUndefined} from "util";

@Component({
  moduleId: module.id,
  selector: 'attendance-list',
  templateUrl: 'html/attendance-list.component.html',
})

export class AttendanceListComponent implements OnInit {
  users: User[];
  user: User = new User;

  edited: boolean = false;
  editedUser: User;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.getUsers();
  }

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
    });
  }

  deleteUser(user: User) {
    console.log('delete user ' + user.username);
    this.userService.deleteUser(user.id, () => this.getUsers());
  }

  editUser(user: User) {
    this.edited = true;
    this.editedUser = user;
  }

  acceptEdit(user: User) {
    this.updateUser(this.editedUser);
    user = this.editedUser;
    this.finishEdit();
  }

  updateUser(user: User) {
    this.userService.updateUser(user.id, user);
  }

  rejectEdit() {
    this.finishEdit();
  }

  private finishEdit() {
    this.edited = false;
    this.editedUser = null;
  }
}
