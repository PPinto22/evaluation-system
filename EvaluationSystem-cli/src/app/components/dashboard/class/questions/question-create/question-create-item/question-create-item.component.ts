import {Component, Input, OnInit} from '@angular/core';
import {Question} from '../../../../../../models/question';
import {Answer} from '../../../../../../models/answer';

@Component({
  selector: 'app-question-create-item',
  templateUrl: './question-create-item.component.html',
  styleUrls: ['./question-create-item.component.css']
})
export class QuestionCreateItemComponent implements OnInit {
  @Input() question: Question;

  private answers: Answer[];
  constructor() { }

  ngOnInit() {
    this.answers = [];
    this.answers.push( new Answer(false, '') );
  }

  public addAnswer() {
    this.answers.push( new Answer(false, '') );
  }

  public saveQuestion() {

  }
}
