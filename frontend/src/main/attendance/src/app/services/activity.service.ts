/**
 * Created by kamil on 03.05.17.
 */
import {Injectable} from '@angular/core';
import {Activity} from "../model/activity";
import {DatabaseService} from "./database.service";

import {Http} from "@angular/http";

@Injectable()
export class ActivityService extends DatabaseService {

  private activityHref = '/activities';

  /*
   * TODO:
   * - get by id
   * - update
   * - delete by id
   * - assign user
   * */

  getActivities(): Promise<Activity[]> {
    return this.http.get(this.backendHref + this.activityHref)
      .toPromise()
      .then(response => response.json() as Activity[])
      .catch(this.handleError);
  }

  addActivity(activity: Activity, callback?: ()=>void): void {
    this.http.post(this.backendHref + this.activityHref, JSON.stringify(
      {
        name: activity.name,
        location: activity.location,
        startDate: activity.startDate
      }),
      {headers: this.headers})
      .toPromise()
      .then(res => {
        res.text();
        if(callback !== undefined)
          callback();
      })
      .catch(this.handleError);
  }

  deleteActivity(id: number, callback?: ()=>void): void {
    this.http.delete(this.composeIdUrl(id))
      .toPromise()
      .then(response =>{
          response.text();
          if(callback !== undefined)
            callback();
      })
      .catch(this.handleError);
  }

  updateActivity(id: number, activity: Activity): void {
    this.http.post(this.composeIdUrl(id), activity)
      .toPromise()
      .then(response => response.text())
      .catch(this.handleError);
  }

  assignUser(activityId, userId: number): void {
    this.http.put(this.composeIdUrl(activityId) + "/users/" + userId.toString(), null)
      .toPromise()
      .then(response => response.text())
      .catch(this.handleError);
  }

  private composeIdUrl(id: number): string {
    return this.backendHref + this.activityHref + '/' + id.toString();
  }

  constructor(private http: Http) {
    super();
  }
}
