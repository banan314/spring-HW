/**
 * Created by kamil on 01.05.17.
 */
enum Sex {MALE, FEMALE};
export class User {
  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
  }

  get age(): number {
    return this._age;
  }

  set age(value: number) {
    this._age = value;
  }

  get sex(): Sex {
    return this._sex;
  }

  set sex(value: Sex) {
    this._sex = value;
  }

  get dateOfBirth(): Date {
    return this._dateOfBirth;
  }

  set dateOfBirth(value: Date) {
    this._dateOfBirth = value;
  }

  get activities(): string[] {
    return this._activities;
  }

  set activities(value: string[]) {
    this._activities = value;
  }

  private _id: number;
  private _username: string;
  private _age: number;
  private _sex: Sex;

  private _dateOfBirth: Date;
  private _activities: string[];
}
