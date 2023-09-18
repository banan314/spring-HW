import {DatabaseService} from './database.service';
import {Activity} from '../model/activity';

export class CheckService extends DatabaseService {

  private checkInPath = '/check-in';
  private checkOutPath = '/check-out';
  private activityAttendancePath = '/activity/attendance';

  checkIn(activity: Activity) {
    return this.post(this.checkInPath, activity);
  }

  checkOut(activity: Activity) {
    return this.post(this.checkOutPath, activity);
  }

  attendActivity(activity: Activity) {
    return this.post(this.activityAttendancePath, activity);
  }
}
