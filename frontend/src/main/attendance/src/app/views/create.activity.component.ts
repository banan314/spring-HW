/**
 * Created by kamil on 01.05.17.
 */
import {Component, OnInit} from '@angular/core';
import {ActivityService} from "../services/activity.service";
import {Activity} from "../model/activity";

@Component({
  moduleId: module.id,
  selector: 'create-activity',
  templateUrl: './html/create-activity.component.html',
  providers: [ActivityService]
})

export class CreateActivityComponent implements OnInit {

  activity: Activity = new Activity;

  addActivity() {
    console.log("add activity");
    this.activityService.addActivity(this.activity);
  }

  constructor(private activityService: ActivityService) {
  }

  ngOnInit() {
  }
}
