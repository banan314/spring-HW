import {Component, OnInit} from '@angular/core';
import {Activity} from '../model/activity';
import {ActivityService} from '../services/activity.service';

@Component({
  selector: 'app-activity-list',
  templateUrl: './activity-list.component.html',
  styleUrls: ['./activity-list.component.css']
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
    console.log('add activity');
    this.activityService.addActivity(this.activity).subscribe(() => this.getActivities());
  }

  getActivities() {
    this.activityService.getActivities()
      .subscribe(data => this.activities = data.body['activities']); // TODO: check
  }

  deleteActivity(activity: Activity): void {
    this.activityService.deleteActivity(activity.id).subscribe (data => this.getActivities());
  }

}
