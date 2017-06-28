import {Component, Input, OnInit} from '@angular/core';
import {Question} from '../../../../../../models/question';

@Component({
  selector: 'app-question-create-item',
  templateUrl: './question-create-item.component.html',
  styleUrls: ['./question-create-item.component.css']
})
export class QuestionCreateItemComponent implements OnInit {
  @Input() question: Question;

  constructor() { }

  ngOnInit() {
  }

}
