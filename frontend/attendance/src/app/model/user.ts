import {Sex} from './sex.enum';

export class User {
  private _id: number;
  private _username: string;
  private _age: number;
  private _sex: Sex;

  private _dateOfBirth: Date;
  private _activities: string[];
  private _email: string;

  constructor(id?: number, username?: string, age?: number, sex?: Sex, dateOfBirth?: Date, email?: string, activities?: string[]) {
    this._id = id;
    this._username = username;
    this._age = age;
    this._sex = sex;
    this._dateOfBirth = dateOfBirth;
    this._activities = activities;
    this._email = email;
  }

  static fromUser(user: User): User {
    return new this(
      user.id,
      user.username,
      user.age,
      user.sex,
      user.dateOfBirth,
      user.email,
      user.activities
    );
  }

  get email(): string {
    return this._email;
  }
  set email(value: string) {
    this._email = value;
  }

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
}
