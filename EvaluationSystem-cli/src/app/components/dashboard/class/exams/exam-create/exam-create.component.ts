import {Component, OnInit, SimpleChanges} from '@angular/core';
import {Question} from '../../../../../models/question';
import {CategoriesService} from '../../../../../services/categories.service';
import {ActivatedRoute} from '@angular/router';
import {QuestionsService} from '../../../../../services/questions.service';
import {ExamsService} from '../../../../../services/exams.service';

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
  private saveAll = false;
  private generateAll = false;

  private questionsIds: number[];

  constructor(
    private question: QuestionsService,
    private route: ActivatedRoute,
    private exam: ExamsService
  ) {
    this.route.params.subscribe(params => {
      this.groupId = +params['group_id'];
    });
    this.questionsIds = [];
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
    if ( this.examCreate.nameExam && this.examCreate.questionNumber && this.examCreate.duration && this.examCreate.dateExam && this.examCreate.hourExam ) {
      if ( this.examCreate.duration > 0) {
        const dateNow = new Date();
        const dateExameChoise = new Date(this.examCreate.dateExam);
        if (dateNow < dateExameChoise) {
          if (this.validateAllQuestions()) {
            this.exam.createExamByGroupId(this.groupId, dateExameChoise.getTime(), this.examCreate.duration, this.examCreate.nameExam, this.questionsIds).subscribe(
              resultado => {
                console.log(resultado);
                console.log('resultado EXAME COMPLETO');
              },
              error => {
                console.log(error);
              }
            );
          }
        } else {
          // TODO avisar data errada
        }
      } else {
        console.log('dada');
      }
    }
  }

  public validateAllQuestions(): boolean {
    let allok = true;
    this.questionsIds = [];
    for ( const quest of this.questions ) {
      if ( !quest.id ) {
        allok = false;
        break;
      }
      this.questionsIds.push(quest.id);
    }
    return allok;
  }

  public validateAll(): Date {
      const p = new Date(this.examCreate.dateExam);
      const hours = this.examCreate.hourExam[0] + this.examCreate.hourExam[1];
      const minutes = this.examCreate.hourExam[3] + this.examCreate.hourExam[4];
      p.setMinutes(+minutes);
      p.setHours(+hours);
      return p;
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

        if (categoryexist.get(+quest.dificulty)) {

          if ( !this.allGenerateNow.get(quest.category).get(+quest.dificulty).find(x => x === quest.id) ) {
            this.allGenerateNow.get(quest.category).get(+quest.dificulty).push(quest.id);
          }

        } else {

          const newArray: number[] = new Array();
          newArray.push(quest.id);
          this.allGenerateNow.get(quest.category).set(+quest.dificulty, newArray);

        }
      } else {

        const newArray: Array<number> = new Array();
        newArray.push(quest.id);
        const newMap: Map<number, number[]> = new Map<number, number[]>();
        newMap.set(+quest.dificulty, newArray);
        this.allGenerateNow.set(quest.category, newMap);

      }

      if (this.updateDificulty) {
        this.updateDificulty = false;
      } else {
        this.updateDificulty = true;
      }
    }, 1);
  }

  public changeNow(): void {
    if (this.updateDificulty) {
      this.updateDificulty = false;
    } else {
      this.updateDificulty = true;
    }
  }

  public generateAllNow(): void {
    this.saveAll = !this.saveAll;
    let allok = true;
    setTimeout( () => {
      for (const quest of this.questions) {
        console.log(quest);
        if (( (quest.dificulty + '') !== '-1' && quest.dificulty) && quest.category) {
          console.log('entrei  aqui');
        } else {
          console.log(' lixo com esta');
          allok = false;
          break;
        }
      }
      console.log(allok)
      console.log('all ok')
      if ( allok ) {
        this.generateAll = true;
      }

    }, 1);
  }




}
