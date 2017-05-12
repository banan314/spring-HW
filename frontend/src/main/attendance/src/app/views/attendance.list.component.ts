/**
 * Created by kamil on 01.05.17.
 */
import {Component, OnInit} from '@angular/core';
import {Activity} from "../model/activity";

@Component({
  moduleId: module.id,
  selector: 'attendance-list',
  templateUrl: 'html/attendance-list.component.html'
})

export class AttendanceListComponent implements OnInit {
  activity :Activity;

  constructor() {
  }

  ngOnInit() {
  }
}
