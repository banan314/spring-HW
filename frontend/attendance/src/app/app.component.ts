import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Attendance manager';
  greetings = 'Sprawdź swoją obecność i dostępne zajęcia. Dodaj zajęcia lub użytkowników.';
}
