import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-app-header',
  templateUrl: './app-header.component.html',
  styleUrls: ['./app-header.component.css']
})
export class AppHeaderComponent {
  title = 'Attendance manager';
  greetings = 'Check your attendance and available activities. Add new activities or users.';
}
