import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Question} from '../../../../models/question';
import {Answer} from '../../../../models/answer';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  private questions: Question[];

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.questions = [];
    const answer: Answer[] = [];
    answer.push( new Answer(false, 'Ola 1') );
    answer.push( new Answer(true, 'Ola 2') );
    const question: Question =  new Question(Question._Normal, 'Spring');
    question.text = 'Como cumprimentar as pessoas \n Qual a melhor forma';
    question.answers = answer;
    question.answers = answer;

    console.log(question);
    this.questions.push( question );
    this.questions.push( question );


  }



}
