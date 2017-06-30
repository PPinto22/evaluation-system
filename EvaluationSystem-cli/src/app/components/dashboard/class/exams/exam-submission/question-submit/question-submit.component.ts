import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-question-submit',
  templateUrl: './question-submit.component.html',
  styleUrls: ['./question-submit.component.css']
})
export class QuestionSubmitComponent implements OnInit {
  @Input() question;

  constructor() { }

  ngOnInit() {
  }

}
