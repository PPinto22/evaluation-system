/**
 * Created by rjaf on 28/06/2017.
 */
export class Answer {

  public id: number;
  public correct: boolean;
  public text: string;

  constructor(correct: boolean, text: string) {
    this.correct = correct;
    this.text = text;
  }
}
