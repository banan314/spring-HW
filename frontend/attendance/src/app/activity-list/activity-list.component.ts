import {Component, OnInit} from '@angular/core';
import {Activity} from '../model/activity';
import {ActivityService} from '../services/activity.service';
import HttpStatus from '../constants/HttpStatus';
import {Router} from '@angular/router';

@Component({
  selector: 'app-activity-list',
  templateUrl: './activity-list.component.html',
  styleUrls: ['./activity-list.component.css']
})
export class ActivityListComponent implements OnInit {

  activities: Activity[];
  activity: Activity = new Activity;

  constructor(private activityService: ActivityService, private router: Router) {
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
      .subscribe(data => {
        if(data.status == HttpStatus.OK) {
          this.activities = data.body['activities'];
        } else if(data.status == HttpStatus.UNAUTHORIZED) {
          this.router.navigate(['login']);
        }
      });
  }

  deleteActivity(activity: Activity): void {
    this.activityService.deleteActivity(activity.id).subscribe(data => this.getActivities());
  }

}
