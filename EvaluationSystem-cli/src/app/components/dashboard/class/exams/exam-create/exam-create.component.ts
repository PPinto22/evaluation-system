import { Component, OnInit } from '@angular/core';
import {Question} from '../../../../../models/question';

declare var page_content_onresize: any;

@Component({
  selector: 'app-exam-create',
  templateUrl: './exam-create.component.html',
  styleUrls: ['./exam-create.component.css']
})
export class ExameCreateComponent implements OnInit {

  private questions: Question[];
  private number_questions: number;
  private categories: string[];

  constructor() { }

  ngOnInit() {
    this.questions = [];
    this.categories = ['Spring'];
    this.questions.push( new Question( Question._Normal, this.categories[0]));

  }


  public deleteQuestion( question: Question): void {
    this.questions.filter( ( question_item ) => {
      return question_item !== question;
    });
  }

  public addQuestion(): void {
    this.questions.push( new Question( Question._Normal, this.categories[0]));
    page_content_onresize();
  }




}
