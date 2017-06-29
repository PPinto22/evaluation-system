import {Answer} from './answer';
export class Question {

  public static _Hard = 3;
  public static _Normal = 2;
  public static _Easy = 1;

  private _id: number;
  private _dificulty: number;
  private _category: string;
  private _answers: Answer[];
  private _text: string;

  constructor(dificulty: number, category: string) {
    this._dificulty = dificulty;
    this._category = category;
  }

  get text(): string {
    return this._text;
  }

  set text(value: string) {
    this._text = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get dificulty(): number {
    return this._dificulty;
  }

  set dificulty(value: number) {
    this._dificulty = value;
  }

  get category(): string {
    return this._category;
  }

  set category(value: string) {
    this._category = value;
  }

  get answers(): Answer[] {
    return this._answers;
  }

  set answers(value: Answer[]) {
    this._answers = value;
  }
}
