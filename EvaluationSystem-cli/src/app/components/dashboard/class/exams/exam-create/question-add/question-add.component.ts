///<reference path="../../../../../../../../node_modules/@angular/core/src/metadata/lifecycle_hooks.d.ts"/>
import {Component, Input, OnInit, EventEmitter, Output, OnChanges, SimpleChanges} from '@angular/core';
import {Question} from '../../../../../../models/question';
import {QuestionsService} from '../../../../../../services/questions.service';

@Component({
  selector: 'app-question-add',
  templateUrl: './question-add.component.html',
  styleUrls: ['./question-add.component.css']
})
export class QuestionAddComponent implements OnInit, OnChanges {

  @Input() question: Question;
  @Input() categories: string[];
  @Input() allQuestionsAvailable: any;
  @Input() groupId: number;
  @Input() updateDificulty: boolean;
  @Input() allGenerateNow: Map<string, Map<number, Array<number>>>;

  @Output() removeQuestion = new EventEmitter();
  @Output() removeFromAllQuestionsAvailable = new EventEmitter();

  private display_details: boolean;
  private toogle_display_details: boolean;
  private dificultys: any[];
  private questionModel: any = {};

  constructor(
    private quest: QuestionsService
  ) {
    this.dificultys = [];
  }

  ngOnInit() {
    this.display_details = false;
    this.toogle_display_details = false;
    if ( this.categories.length === 0) {
      setTimeout(() => {
        console.log(this.categories);
        this.questionModel.category = this.categories[0];
        this.selectDificultyAvailable();
      }, 1000);
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if ( !this.question.id) {
      this.display_details = false;
      this.toogle_display_details = false;
      this.questionModel.category = this.categories[0];
      this.selectDificultyAvailable();
    }
  }

  public selectDificultyAvailable(): void {
    setTimeout(() => {
      const categ = this.questionModel.category;
      this.dificultys = [];
      for (const dific in this.allQuestionsAvailable[categ]) {
        if (+dific === 1 && !this.dificultys.find(x => x.value === 1)) {
          if ( this.allQuestionsAvailable[categ][1].available > 0 ) {
            this.dificultys.push({value: 1, dificulty: 'Easy'});
          }
        }
        if (+dific === 2 && !this.dificultys.find(x => x.value === 2)) {
          if ( this.allQuestionsAvailable[categ][2].available > 0 ) {
            this.dificultys.push({value: 2, dificulty: 'Normal'});
          }
        }
        if (+dific === 3 && !this.dificultys.find(x => x.value === 3)) {
          if ( this.allQuestionsAvailable[categ][3].available > 0 ) {
            this.dificultys.push({value: 3, dificulty: 'Hard'});
          }
        }
      }
      if ( this.dificultys.length > 0 ) {
        console.log(this.dificultys[0].value);
        this.questionModel.dificult = this.dificultys[0].value;
      } else {
        this.questionModel.dificult = '';
      }
    }, 1);
  }

  toggleDiplay(): void {
    this.toogle_display_details = !this.toogle_display_details;
  }

  public removeQuestionNow(): void {
    this.removeQuestion.emit(this.question);
  }

  public generateQuestion(): void {
    if (this.questionModel.dificult) {
      if (this.question.id) {
        // TODO fazer o metodo que volta a colocar lá essa questão que gerou visto que é a segunda vez que ele gera.
        this.removeFromGenerate(this.question);
      }
      let allExclude = [];
      if ( this.allGenerateNow.get(this.questionModel.category) ) {
        if ( this.allGenerateNow.get(this.questionModel.category).get(this.questionModel.dificult) ) {
          allExclude = this.allGenerateNow.get(this.questionModel.category).get(this.questionModel.dificult);
        }
      }
      console.log(allExclude);
      this.quest.createQuestionByGenerate(this.groupId, this.questionModel.category, this.questionModel.dificult, allExclude).subscribe(
        resultado => {
          this.question.category = resultado.category;
          this.question.dificulty = resultado.difficulty;
          this.question.text = resultado.text;
          this.question.id = resultado.id;
          this.question.answers = resultado.answers;
          this.removeFromallQuestionsAvailable();
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  public removeFromallQuestionsAvailable(): void {
    const p = this.questionModel.dificult;
    this.allQuestionsAvailable[this.questionModel.category][p].available = this.allQuestionsAvailable[this.questionModel.category][p].available - 1;
    this.removeFromAllQuestionsAvailable.emit(this.question.id);
  }

  public removeFromGenerate(question: Question): void {
    const p = question.dificulty;
    const indexArray = this.allGenerateNow.get(question.category).get(question.dificulty).indexOf(question.id);
    this.allGenerateNow.get(question.category).get(question.dificulty).splice(indexArray, 1);
    this.allQuestionsAvailable[this.questionModel.category][p].available = this.allQuestionsAvailable[this.questionModel.category][p].available + 1;
  }
}
