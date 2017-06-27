import {User} from './user';
/**
 * Created by rjaf on 24/06/2017.
 */
export class Class {

  private _id: number;
  private _name: string;
  private _abbreviation: string;
  private _user: User;

  constructor( id: number, name: string, abbreviation: string, user: User) {
    this._id = id;
    this._name = name;
    this._abbreviation = abbreviation;
    this._user = user;
  }

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

  get abbreviation(): string {
    return this._abbreviation;
  }

  set abbreviation(value: string) {
    this._abbreviation = value;
  }
}
