import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ExamsService} from '../../../../../../services/exams.service';

@Component({
  selector: 'app-question-submit',
  templateUrl: './question-submit.component.html',
  styleUrls: ['./question-submit.component.css']
})
export class QuestionSubmitComponent implements OnInit, OnChanges {

  @Input() question;
  @Input() submissionId: number;
  @Input() choiseanswersId: number;
  @Input() saveAll: boolean;

  saveOk = false;
  oldsaveAll = false;

  private correctAnswer: any = {};

  constructor(
    private examService: ExamsService
  ) { }

  ngOnInit() {
    this.oldsaveAll = this.saveAll;
    if ( this.choiseanswersId !== -1) {
      this.correctAnswer.correta = this.choiseanswersId;
    } else {
      this.correctAnswer.correta = -1;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if ( this.oldsaveAll !== this.saveAll) {
      this.oldsaveAll = this.saveAll;
      this.saveAnswerNow();
    }
  }

  public saveAnswerNow(): void {
    if ( this.correctAnswer.correta !== -1 ) {
      if ( this.correctAnswer.correta !== this.choiseanswersId) {
        this.choiseanswersId = this.correctAnswer.correta;
        this.examService.putExamAnswerSubmission(this.submissionId, this.question.id, this.correctAnswer.correta).subscribe(
          result => {
            this.saveOk = true;
          },
          error => {
            console.log(error);
          }
        );
      }
      this.saveOk = true;
    }
  }

  public getname(): string {
    return 'name' + this.question.id;
  }

}
