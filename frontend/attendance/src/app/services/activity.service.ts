import {DatabaseService} from './database.service';
import {Activity} from '../model/activity';

export class ActivityService extends DatabaseService {

  private activityHref = '/activities';

  getActivities() {
    return this.get(this.activityHref);
  }

  getActivityById(id: number) {
    return this.get(this.composeIdUrl(id));
  }

  addActivity(activity: Activity) {
    return this.post(this.activityHref, activity);
  }

  deleteActivity(id: number) {
    return this.delete(this.composeIdUrl(id));
  }

  updateActivity(id: number, activity: Activity) {
    return this.post(this.composeIdUrl(id), activity);
  }

  assignUser(activityId, userId: number) {
    return this.put(this.composeIdUrl(activityId) + '/users/' + userId.toString());
  }

  private composeIdUrl(id: number): string {
    return this.activityHref + '/' + id.toString();
  }

}
