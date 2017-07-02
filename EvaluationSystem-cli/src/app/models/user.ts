/**
 * Created by rjaf on 30/05/2017.
 */
export class User {
  public static _Teacher = 'Teacher';
  public static _Student = 'Student';

  private _id: number;
  private _email: string;
  private _firstName: string;
  private _lastName: string;
  private _type: string;
  private _token: string;
  private _active: boolean;

  constructor(id: number, email: string, firstName: string, lastName: string, type: string, token: string) {
    this._id = id;
    this._email = email;
    this._firstName = firstName;
    this._lastName = lastName;
    this._type = type;
    this._token = token;
  }


  get active(): boolean {
    return this._active;
  }

  set active(value: boolean) {
    this._active = value;
  }

  isTeacher(): boolean {
    return this._type === User._Teacher ? true : false;
  }

  isStudent(): boolean {
    return this._type === User._Student ? true : false;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }

  get token(): string {
    return this._token;
  }

  set token(value: string) {
    this._token = value;
  }


  get firstName(): string {
    return this._firstName;
  }

  set firstName(value: string) {
    this._firstName = value;
  }

  get lastName(): string {
    return this._lastName;
  }

  set lastName(value: string) {
    this._lastName = value;
  }

  getName(): string {
    return this.firstName + ' ' + this.lastName;
  }
}
