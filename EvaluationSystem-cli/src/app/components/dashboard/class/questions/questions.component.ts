import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Question} from '../../../../models/question';
import {Answer} from '../../../../models/answer';
import {QuestionsService} from '../../../../services/questions.service';
import {CategoriesService} from '../../../../services/categories.service';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  private questions: Question[];
  private questionModel: any = {};
  private allQuestionsAvailable: any;
  private dificultys: any[];
  private categoriesName: string[];
  private classId: number;
  private allQuestion: Map< string, Map<number, Question[]>>;

  constructor(
    private router: Router,
    private question: QuestionsService,
    private route: ActivatedRoute,
    private categorie: CategoriesService
  ) {
    this.route.params.subscribe(params => {
      this.classId = +params['class_id'];
    });
    this.dificultys = [];
    this.categoriesName = [];
    this.allQuestion = new Map< string, Map<number, Question[]>>();
  }

  ngOnInit() {
    this.questions = [];
    this.getAllQuestions();
    this.dificultys.push({value: 1, dificulty: 'Easy'});
    this.dificultys.push({value: 2, dificulty: 'Normal'});
    this.dificultys.push({value: 3, dificulty: 'Hard'});
    this.questionModel.difi = 1;
  }

  public getQuestion(): void {
    this.questions = [];
    setTimeout( () => {
      if ( this.allQuestion.get(this.questionModel.category) ) {
        if ( this.allQuestion.get(this.questionModel.category).get(+this.questionModel.difi) ) {
          this.questions = this.allQuestion.get(this.questionModel.category).get(+this.questionModel.difi);
        }
      }
    }, 1);
  }

  public getAllCategories(): void {
    this.categorie.getCategoriesByClasse(this.classId).subscribe(
      result => {
        let haveCateg = 0;
        for ( let categ of result) {
          this.categoriesName.push(categ);
          haveCateg++;
        }
        if ( haveCateg !== 0) {
          this.questionModel.category =  this.categoriesName[0];
          this.getQuestion();
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  public getAllQuestions(): void {
    this.question.getAllQuestionsFromClasse( this.classId).subscribe(
      result => {
        for ( let quest of result ) {

          if ( this.allQuestion.get(quest.category) ) {
            if ( this.allQuestion.get(quest.category).get(+quest.difficulty) ){
              this.allQuestion.get(quest.category).get(+quest.difficulty).push(quest);
            } else {
              const newArrayQuest = new Array();
              newArrayQuest.push(quest);
              this.allQuestion.get(quest.category).set(+quest.difficulty, newArrayQuest);
            }
          } else {
            const newArrayQuest = new Array();
            newArrayQuest.push(quest);
            const newMapDificulty = new Map<number, Question[]>();
            newMapDificulty.set(+quest.difficulty, newArrayQuest);
            this.allQuestion.set(quest.category, newMapDificulty);
          }
        }

        this.getAllCategories();
      },
      error => {
        console.log(error);
      }
    );
  }



}
