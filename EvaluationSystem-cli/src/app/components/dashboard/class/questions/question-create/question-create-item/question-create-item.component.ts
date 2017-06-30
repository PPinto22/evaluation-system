import {Component, Input, OnInit, EventEmitter, Output, SimpleChanges, OnChanges} from '@angular/core';
import {Question} from '../../../../../../models/question';
import {Answer} from '../../../../../../models/answer';
import {QuestionsService} from '../../../../../../services/questions.service';

@Component({
  selector: 'app-question-create-item',
  templateUrl: './question-create-item.component.html',
  styleUrls: ['./question-create-item.component.css']
})
export class QuestionCreateItemComponent implements OnInit, OnChanges {
  @Input() question: Question;
  @Input() allCategories: string[];
  @Input() saveAll: boolean;
  @Input() classId: number;

  @Output() saveQuestionButton = new EventEmitter();
  @Output() questionPostOK = new EventEmitter();
  @Output() removeQuestio = new EventEmitter();

  private changeSave: boolean;
  private saveanswer: boolean;
  private answers: Answer[];
  quest: any = {};
  private correctAnswerIndex: number;
  private needSelectRadio: boolean;
  private oldsaveAll: boolean;
  private removeAnswer: boolean;

  constructor(
    private questionService: QuestionsService
  ) {
  }

  ngOnInit() {
    this.quest.dificulty = Question._Easy;
    console.log(this.quest);
    this.answers = [];
    this.saveanswer = false;
    this.changeSave = false;
    this.needSelectRadio = false;
    this.removeAnswer = false;
    this.correctAnswerIndex = -1;
    this.oldsaveAll = this.saveAll;
    this.answers.push( new Answer(false, '') );
    this.answers.push( new Answer(false, '') );
  }

  ngOnChanges(changes: SimpleChanges) {
    if ( this.saveAll !== this.oldsaveAll) {
      this.oldsaveAll = this.saveAll;
      this.needSelectRadio = false;
      if ( this.saveAll === true) {
        this.saveQuestion();
      }
    } else {
      this.needSelectRadio = false;
    }
  }

  public viewRemoveAnswer(): boolean {
    if ( this.answers.length > 2 ) {
      this.removeAnswer = true;
      return true;
    }
    this.removeAnswer = false;
    return false;
  }

  public addAnswer() {
    this.answers.push( new Answer(false, '') );
    if ( this.answers.length > 2 ) {
      this.removeAnswer = true;
    } else {
      this.removeAnswer = false;
    }
  }

  public saveQuestion() {
    this.saveanswer = true;
    if ( this.correctAnswerIndex !== -1 ) {
      this.needSelectRadio = false;

      if (this.changeSave === true) {
        this.changeSave = false;
      } else {
        this.changeSave = true;
      }

      this.question.category = this.quest.category;
      this.question.dificulty = this.quest.dificulty;
      this.question.text = this.quest.questionText;
      this.question.answers = [];
      this.checkAnswer();

    } else {
      this.needSelectRadio = true;
    }
  }

  public checkAnswer(): void {
    setTimeout(() => {
      let allOk = true;
      let index = 0;

      for (const answer of this.answers) {
        if (!answer.text) {
          allOk = false;
          break;
        }

        if ( this.correctAnswerIndex === index ) {
          answer.correct = true;
        } else {
          answer.correct = false;
        }

        this.question.answers.push(answer);

        index++;
      }

      if (allOk) {
        this.addQuestion();
      }

    }, 1);
  }

  public addQuestion(): void {
    console.log(this.question);
    this.questionService.createQuestionByClasse(this.classId, this.question.text, this.question.category, this.question.dificulty, this.question.answers).subscribe(
      resultado => {
        if ( this.saveAll === true ) {
          this.questionPostOK.emit(this.question);
        } else {
          this.saveQuestionButton.emit(this.question);
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  public correctAnswer($event) {
    this.correctAnswerIndex = $event;
  }

  public removeAnswerFromQuestion($event) {
    const indexanswer = this.answers.indexOf($event);
    this.answers.splice(indexanswer, 1);
    this.viewRemoveAnswer();
  }

  public removeQuestion(): void {
   this.removeQuestio.emit(this.question);
  }
}
