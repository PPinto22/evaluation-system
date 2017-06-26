import {Component, Input, OnInit} from '@angular/core';
import {Question} from '../../../../../models/question';

@Component({
  selector: 'app-question-add',
  templateUrl: './question-add.component.html',
  styleUrls: ['./question-add.component.css']
})
export class QuestionAddComponent implements OnInit {
  @Input() question: Question;

  private display_details: boolean;
  private toogle_display_details: boolean;
  constructor() { }

  ngOnInit() {
    this.display_details = false;
    this.toogle_display_details = false;
  }

  toggleDiplay(): void {
    this.toogle_display_details = !this.toogle_display_details;
  }
}
