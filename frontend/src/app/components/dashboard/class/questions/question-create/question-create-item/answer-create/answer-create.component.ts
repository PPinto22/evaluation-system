import {Component, Input, OnChanges, OnInit, Output, SimpleChanges, EventEmitter} from '@angular/core';
import {Answer} from '../../../../../../../models/answer';

@Component({
  selector: 'app-answer-create',
  templateUrl: './answer-create.component.html',
  styleUrls: ['./answer-create.component.css']
})
export class AnswerCreateComponent implements OnInit, OnChanges {
  @Input() answer: Answer;
  @Input() saveanswer: boolean;
  @Input() i: number;
  @Input() changeSave: boolean;
  @Input() viewRemoveAnswer: boolean;

  @Output() clickRadio = new EventEmitter();
  @Output() removeAnswer = new EventEmitter();

  answe: any = {};
  oldsaveanswer: boolean;
  oldViewRemoveAnswer: boolean;

  constructor() { }

  ngOnInit() {
    this.oldsaveanswer = this.saveanswer;
  }

  ngOnChanges(changes: SimpleChanges) {
    this.answer.text = this.answe.answerText;
    this.oldViewRemoveAnswer = this.viewRemoveAnswer;
  }

  public saveAnswer(): void {
    this.answer.text = this.answe.answerText;
  }

  public getValue(): void {
    this.clickRadio.emit(this.i);
  }

  public removeAnserFromQuestion(): void {
    this.removeAnswer.emit(this.answer);
  }

}
