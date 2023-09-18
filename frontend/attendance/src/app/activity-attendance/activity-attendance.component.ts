import { Component, OnInit } from '@angular/core';
import {CheckService} from "../services/check.service";
import HttpStatus from "../constants/HttpStatus";

@Component({
  selector: 'app-landing',
  templateUrl: './activity-attendance.component.html',
  styleUrls: ['./activity-attendance.component.css']
})
export class ActivityAttendanceComponent implements OnInit {

  checkEvents: string[];

  constructor(private checkService: CheckService) { }

  ngOnInit(): void {
    this.getCheckEvents();
  }

  getCheckEvents() {
    this.checkService.getActivityAttendance().subscribe(
      res => {
        if(res.status == HttpStatus.OK) {
          this.checkEvents = res.body as string[];
        }
      }
    )
  }
}
