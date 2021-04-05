import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LogInService} from '../services/log-in.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  username: string;
  password: string;
  rememberMe: boolean;

  constructor(private router: Router, private logInService: LogInService) { }

  ngOnInit() {
  }

  login(username: string, password: string) {
    this.logInService.login(username, password)
      .subscribe(() => {
        this.router.navigate(['/activities']);
      });
  }
}
