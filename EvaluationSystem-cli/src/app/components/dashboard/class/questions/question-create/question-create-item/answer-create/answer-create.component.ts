import {Component, Input, OnInit} from '@angular/core';
import {Answer} from '../../../../../../../models/answer';

@Component({
  selector: 'app-answer-create',
  templateUrl: './answer-create.component.html',
  styleUrls: ['./answer-create.component.css']
})
export class AnswerCreateComponent implements OnInit {
  @Input() answer: Answer;

  constructor() { }

  ngOnInit() {
  }

}
