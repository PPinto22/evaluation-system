import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Question} from '../../../../../models/question';

@Component({
  selector: 'app-question-create',
  templateUrl: './question-create.component.html',
  styleUrls: ['./question-create.component.css']
})
export class QuestionCreateComponent implements OnInit {

  private questions: Question[];
  private categories: string[];

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.questions = [];
    this.questions.push( new Question( Question._Normal, '') );
  }

  public saveQuestions(): void {
    // this.router.navigate();
  }

  public moreQuestion(): void {
    this.questions.push( new Question( Question._Normal, '') );

  }

}
