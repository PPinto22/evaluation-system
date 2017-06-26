import {Component, Input, OnInit} from '@angular/core';
import {Question} from 'app/models/question';

@Component({
  selector: 'app-question-preview',
  templateUrl: './question-preview.component.html',
  styleUrls: ['./question-preview.component.css']
})
export class QuestionPreviewComponent implements OnInit {
  @Input() question: Question;


  constructor() { }

  ngOnInit() {
  }

}
