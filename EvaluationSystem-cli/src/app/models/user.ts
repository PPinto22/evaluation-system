/**
 * Created by rjaf on 30/05/2017.
 */
export class User {
  private static _Teacher:string = "teacher";

  private _id: number;
  private _email: string;
  private _password:string;
  private _name:string;
  private _type: string
  private _token: string

  constructor(id: number, email:string, name:string, type:string, token:string) {
    this._id = id;
    this._email = email;
    this._password = "teste";
    this._name = name;
    this._type = type;
    this._token = token;
  }

  isTeacher():boolean{
    return this._type === User._Teacher ? true : false;
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

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
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
}
