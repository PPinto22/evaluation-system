import {Component, Input, OnInit} from '@angular/core';
import {Question} from 'app/models/question';

@Component({
  selector: 'app-question-preview',
  templateUrl: './question-preview.component.html',
  styleUrls: ['./question-preview.component.css']
})
export class QuestionPreviewComponent implements OnInit {
  @Input() question: Question;
  @Input() choiseanswersId: number;

  constructor() { }

  ngOnInit() {
  }

  getDifficulty(): string {
    if (this.question.dificulty === 1 ) {
      return 'Easy';
    } else {
      if ( this.question.dificulty === 2) {
        return 'Normal';
      }
      return 'Hard';
    }
  }

}
