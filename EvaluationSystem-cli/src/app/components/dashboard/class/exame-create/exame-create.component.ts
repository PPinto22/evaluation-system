import { Component, OnInit } from '@angular/core';
import {Question} from '../../../../models/question';

@Component({
  selector: 'app-exame-create',
  templateUrl: './exame-create.component.html',
  styleUrls: ['./exame-create.component.css']
})
export class ExameCreateComponent implements OnInit {

  private questions: Question[];
  private number_questions: number;
  private categories: string[];

  constructor() { }

  ngOnInit() {
    this.questions = [];
    this.questions.push( new Question( Question._Normal, 'Categoria 1'));
    this.questions.push( new Question( Question._Normal, 'Categoria 1'));
    this.questions.push( new Question( Question._Normal, 'Categoria 1'));

  }




}
