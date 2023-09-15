import {Component, OnInit} from '@angular/core';
import {User} from '../model/user';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import HttpStatus from '../constants/HttpStatus';

@Component({
  selector: 'app-attendance-list',
  templateUrl: './attendance-list.component.html',
  styleUrls: ['./attendance-list.component.css']
})

export class AttendanceListComponent implements OnInit {

  users: User[];
  user: User = new User;

  edited = false;
  editedUser: User;

  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.getUsers();
  }

  addUser() {
    this.userService
      .addUser(this.user)
      .subscribe(this.getUsers);
  }

  getUsers() {
    this.userService.getUsers()
      .subscribe(data => {
        if (data.status == HttpStatus.OK) {
          this.users = data.body as User[];
        } else if (data.status == HttpStatus.UNAUTHORIZED) {
          this.router.navigate(['/login']);
        }
      });
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user.id).subscribe(this.getUsers);
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
