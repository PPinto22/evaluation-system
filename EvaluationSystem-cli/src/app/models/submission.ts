import {Exam} from './exam';
import {User} from "./user";
/**
 * Created by rjaf on 01/07/2017.
 */
export class Submission {

  private _id: number;
  private _score: number;
  private _exam: Exam;
  private _user: User;
  private _total: number;
  private _correct: number;

  constructor(id: number, score: number) {
    this._id = id;
    this._score = score;
  }


  get total(): number {
    return this._total;
  }

  set total(value: number) {
    this._total = value;
  }

  get correct(): number {
    return this._correct;
  }

  set correct(value: number) {
    this._correct = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get score(): number {
    return this._score;
  }

  set score(value: number) {
    this._score = value;
  }

  get exam(): Exam {
    return this._exam;
  }

  set exam(value: Exam) {
    this._exam = value;
  }

  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }
}
