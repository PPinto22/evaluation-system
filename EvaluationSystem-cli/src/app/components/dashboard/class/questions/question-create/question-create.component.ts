import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Question} from '../../../../../models/question';
import {CategoriesService} from '../../../../../services/categories.service';

@Component({
  selector: 'app-question-create',
  templateUrl: './question-create.component.html',
  styleUrls: ['./question-create.component.css']
})
export class QuestionCreateComponent implements OnInit {

  private classId: number;
  private questions: Question[];
  private categories: string[];
  private saveAll = false;
  private justOnQuestion = false;
  private questionsAddOk = false;


  constructor(
    private router: Router,
    private categorie: CategoriesService,
    private route: ActivatedRoute
  ) {
    this.categories = [];
  }

  ngOnInit() {
    this.questions = [];
    this.questions.push( new Question( Question._Normal, '') );
    this.route.params.subscribe(params => {
      this.classId = +params['class_id'];
      this.getAllCategories();
    });
  }

  public getAllCategories(): void {
    this.categorie.getCategoriesByClasse(this.classId).subscribe(
      resultado => {
        for ( const categorie of resultado) {
          this.categories.push(categorie);
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  public saveQuestions(): void {
    // this.router.navigate();
  }

  public moreQuestion(): void {
    this.saveAll = false;
    this.questions.push( new Question( Question._Normal, '') );
  }


  public saveQuestion(): void {
    const length = this.questions.length;
    this.saveAll = true;
      if ( length === this.questions.length ) {
        this.saveAll = false;
        setTimeout( () => {
          this.saveAll = true;
        }, 1);
      } else {
        this.saveAll = false;
      }
  }

  public questionPostOk( $event ): void {
    this.deleteQuestion( $event );
    this.questionsAddOk = true;
    this.justOnQuestion = false;
  }

  public saveQuestionButton( $event ): void {
    this.deleteQuestion( $event );
    this.justOnQuestion = true;
    this.questionsAddOk = false;
    this.saveAll = false;
  }

  public deleteQuestion($event): void {
    const indexQuestion = this.questions.indexOf($event);
    this.questions.splice(indexQuestion, 1);
  }

  public removeQuestion($event): void {
    this.deleteQuestion($event);
  }
}
