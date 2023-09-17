import {Component, Input} from '@angular/core';
import {Activity} from "../../model/activity";
import {ActivityService} from "../../services/activity.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-activity-card',
  templateUrl: './activity-card.component.html',
  styleUrls: ['./activity-card.component.css']
})
export class ActivityCardComponent {

  constructor(private activityService: ActivityService, private router: Router) {
  }

  @Input() i: number;
  @Input() activity: Activity;
  @Input() deleteActivityCallBack: () => void;

  deleteActivity(activity: Activity): void {
    this.activityService.deleteActivity(activity.id).subscribe(this.deleteActivityCallBack);
  }
}
