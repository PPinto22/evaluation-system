import {Component, OnInit, SimpleChanges} from '@angular/core';
import {Question} from '../../../../../models/question';
import {CategoriesService} from '../../../../../services/categories.service';
import {ActivatedRoute} from '@angular/router';
import {QuestionsService} from '../../../../../services/questions.service';

declare var page_content_onresize: any;

@Component({
  selector: 'app-exam-create',
  templateUrl: './exam-create.component.html',
  styleUrls: ['./exam-create.component.css']
})
export class ExamCreateComponent implements OnInit {

  private questions: Question[];
  private number_questions: number;
  private examCreate: any = {};
  private groupId: number;
  private categoriesName: string [];
  private allQuestionsAvailable: any = {};
  private updateDificulty = false;
  private allGenerateNow: Map<string, Map<number, Array<number>>>;

  constructor(
    private question: QuestionsService,
    private route: ActivatedRoute
  ) {
    this.route.params.subscribe(params => {
      this.groupId = +params['group_id'];
    });
    this.categoriesName = [];
    this.allGenerateNow = new Map<string, Map<number, Array<number>>>();
  }

  ngOnInit() {
    this.questions = [];
    this.number_questions = 1;
    this.examCreate.questionNumber = 1;
    this.questions.push( new Question( Question._Normal, ''));
    this.getAllQuestionsAvailable();
  }

  public focusOutQuestionNumber(): void {
    console.log('vou tentar');
    if ( this.examCreate.questionNumber > this.number_questions ) {
      let addQuestions = this.examCreate.questionNumber - this.number_questions;
      while ( addQuestions > 0 ) {
        addQuestions--;
        this.addQuestion();
      }
      this.number_questions = this.examCreate.questionNumber;
    }
  }

  public deleteQuestion( question: Question): void {
    this.questions.filter( ( question_item ) => {
      return question_item !== question;
    });
  }

  public addQuestion(): void {
    this.questions.push( new Question( Question._Normal, ''));
    this.number_questions++;
    this.examCreate.questionNumber = this.number_questions;
    page_content_onresize();
  }

  public createExam(): void {
    if ( this.examCreate.nameExam && this.examCreate.questionNumber && this.examCreate.dateExam && this.examCreate.hourExam ) {

    }
  }

  public validateAll(): void {
      const p = new Date(this.examCreate.dateExam);
      const hours = this.examCreate.hourExam[0] + this.examCreate.hourExam[1];
      const minutes = this.examCreate.hourExam[3] + this.examCreate.hourExam[4];
      p.setMinutes(+minutes);
      p.setHours(+hours);
      console.log(p);
  }

  public removeQuest($event): void {
    if ( this.number_questions > 1 ) {
      const indexQuestion = this.questions.indexOf($event);
      this.questions.splice(indexQuestion, 1);
      this.number_questions--;
      if (this.examCreate.questionNumber > 1) {
        this.examCreate.questionNumber--;
      }
    }
  }


  public getAllQuestionsAvailable(): void {
    this.question.getAllQuestionsAvailableGroup(this.groupId).subscribe(
      resultado => {
        this.allQuestionsAvailable = resultado;
        for ( const nameCategory in resultado) {
          this.categoriesName.push(nameCategory);
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  public removeFromAllQuestionsAvailable($event): void {
    setTimeout( () => {
      const quest = this.questions.find(x => x.id === $event)
      if (this.allGenerateNow.get(quest.category)) {

        const categoryexist = this.allGenerateNow.get(quest.category);

        if (categoryexist.get(quest.dificulty)) {

          if ( !this.allGenerateNow.get(quest.category).get(quest.dificulty).find(x => x === quest.id) ) {
            this.allGenerateNow.get(quest.category).get(quest.dificulty).push(quest.id);
          }

        } else {

          const newArray: number[] = new Array();
          newArray.push(quest.id);
          this.allGenerateNow.get(quest.category).set(quest.dificulty, newArray);

        }
      } else {

        const newArray: Array<number> = new Array();
        newArray.push(quest.id);
        const newMap: Map<number, number[]> = new Map<number, number[]>();
        newMap.set(quest.dificulty, newArray);
        this.allGenerateNow.set(quest.category, newMap);

      }

      if (this.updateDificulty) {
        this.updateDificulty = false;
      } else {
        this.updateDificulty = true;
      }
    }, 1);
  }




}
