/**
 * Created by kamil on 09.05.17.
 */

import {Component, OnInit} from '@angular/core';
import {ActivityService} from "../services/activity.service";
import {Activity} from "../model/activity";

@Component({
  moduleId: module.id,
  selector: 'activity-list',
  templateUrl: './html/activity-list.component.html'
})

export class ActivityListComponent implements OnInit {

  activities: Activity[];
  activity: Activity = new Activity;

  constructor(private activityService: ActivityService) {
  }

  ngOnInit() {
    this.getActivities();
  }

  addActivity() {
    console.log("add activity");
    this.activityService.addActivity(this.activity, ()=>this.getActivities());
  }

  getActivities() {
    this.activityService.getActivities()
      .then(res => this.activities = res);
  }
}
