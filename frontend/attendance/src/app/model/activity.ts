import {User} from './user';

export class Activity {
  get users(): User[] {
    return this._users;
  }

  set users(value: User[]) {
    this._users = value;
  }

  private _id: number;
  private _name: string;
  private _startDate: Date;
  private _location: string;
  private _users: User[];

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get startDate(): Date {
    return this._startDate;
  }
  set startDate(value: Date) {
    this._startDate = value;
  }
  get location(): string {
    return this._location;
  }

  set location(value: string) {
    this._location = value;
  }
}
