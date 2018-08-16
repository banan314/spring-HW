import { Injectable } from '@angular/core';
import {DatabaseService} from './database.service';
import {Activity} from '../model/activity';

@Injectable({
  providedIn: 'root'
})
export class ActivityService extends DatabaseService {

  private activityHref = '/activities';

  getActivities() {
    return this.http.get(this.backendHref + this.activityHref, {headers: this.prepareHeaders()});
  }

  getActivityById(id: number) {
    return this.http.get(this.composeIdUrl(id), {headers: this.prepareHeaders()});
  }

  addActivity(activity: Activity) {
    return this.http.post(this.backendHref + this.activityHref, JSON.stringify(
      {
        name: activity.name,
        location: activity.location,
        startDate: activity.startDate
      }),
      {headers: this.prepareHeaders()});
  }

  deleteActivity(id: number) {
    return this.http.delete(this.composeIdUrl(id));
  }

  updateActivity(id: number, activity: Activity) {
    return this.http.post(this.composeIdUrl(id), activity);
  }

  assignUser(activityId, userId: number) {
    return this.http.put(this.composeIdUrl(activityId) + '/users/' + userId.toString(), null);
  }

  private composeIdUrl(id: number): string {
    return this.backendHref + this.activityHref + '/' + id.toString();
  }

}
