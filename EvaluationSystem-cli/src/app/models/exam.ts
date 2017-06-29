import {Group} from './group';
/**
 * Created by pedro on 26-06-2017.
 */
export class Exam {
  private _id: number;
  private _name: string;
  private _beginDate: Date;
  private _duration: number;
  private _group: Group;

  constructor(name: string, beginDate: number, duration: number ) {
    this._name = name;
    this._beginDate = new Date(beginDate);
    this._duration = duration;
  }

  get id(): number {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get beginDate(): Date {
    return this._beginDate;
  }

  get duration(): number {
    return this._duration;
  }

  get group(): Group {
    return this._group;
  }

  set id(value: number) {
    this._id = value;
  }

  set name(value: string) {
    this._name = value;
  }

  set beginDate(value: Date) {
    this._beginDate = value;
  }

  set duration(value: number) {
    this._duration = value;
  }

  set group(value: Group) {
    this._group = value;
  }
}
