import {Answer} from './answer';
export class Question {

  public static _Hard = 3;
  public static _Normal = 2;
  public static _Easy = 1;

  public id: number;
  public dificulty: number;
  public category: string;
  public answers: Answer[];
  public text: string;

  constructor(dificulty: number, category: string) {
    this.dificulty = dificulty;
    this.category = category;
  }
}
