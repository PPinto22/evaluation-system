/**
 * Created by rjaf on 28/06/2017.
 */
export class Answer {

  private _id: number;
  private _checked: boolean;
  private _text: string;


  constructor(checked: boolean, text: string) {
    this._checked = checked;
    this._text = text;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get checked(): boolean {
    return this._checked;
  }

  set checked(value: boolean) {
    this._checked = value;
  }

  get text(): string {
    return this._text;
  }

  set text(value: string) {
    this._text = value;
  }
}
