import {Class} from './class';
/**
 * Created by pedro on 26-06-2017.
 */
export class Group {
  private _id: number;
  private _name: string;
  private _class: Class;

  constructor( id: number, name: string, classe: Class) {
    this._id = id;
    this._name = name;
    this._class = classe;
  }

  get id(): number {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get class(): Class {
    return this._class;
  }

  set id(value: number) {
    this._id = value;
  }

  set name(value: string) {
    this._name = value;
  }

  set class(value: Class) {
    this._class = value;
  }
}
