import { Component, OnInit } from '@angular/core';
import {Question} from '../../../../../models/question';
import {ExamsService} from '../../../../../services/exams.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Exam} from '../../../../../models/exam';
import {BreadCrumbService} from '../../../../../services/breadcrumb.service';

@Component({
  selector: 'app-exam-submission',
  templateUrl: './exam-submission.component.html',
  styleUrls: ['./exam-submission.component.css']
})
export class ExamSubmissionComponent implements OnInit {

  private examId: number;
  private exam: any = {};
  private questions: Question[];
  private choiseanswersId: number [];
  private groupId: number;
  private submissionId: number;
  private nameExam: string;
  private saveAll: boolean;

  constructor(
    private breadCrumb: BreadCrumbService,
    private examsService: ExamsService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    // para teste
    this.saveAll = false;
    this.choiseanswersId = [];
    this.questions = [];
    const question1 = new Question(1, 'spring');
    question1.id = 1;
    const question2 = new Question(2, 'angular');
    question2.id = 1;
    this.questions.push( question1 );
    this.questions.push( question2);

    this.route.params.subscribe( params => {
      this.examId = +params['exam_id'];
      this.groupId = +params['group_id']
      this.getExam( this.examId );
      this.breadCrumb.setBreadCrum(['Exam OnGoing']); // FIXME alterações
    });

  }

  private getExam( exam_id: number ): void {
    this.examsService.getSubmissionsByExamByGroupId( this.examId, this.groupId) .subscribe(
        resultado => {
        if ( resultado.length === 0) {
          this.createExamSubmission();
        } else {
          this.submissionId = resultado[0].id;
          this.getExamSubmission();
        }
      },
      error => {
        console.log(error);
      }
      );
  }

  public getExamSubmission(): void {
    this.examsService.getBySubmission(this.submissionId).subscribe(
      result => {
        this.exam = result;
        this.nameExam = result.exam.name;
        this.getAllQuestions(this.exam.questions);
      },
      error => {
        console.log(error);
      }
    );
  }

  public createExamSubmission(): void {
    this.examsService.createExameSubmission( this.examId ).subscribe(
      result => {
        this.submissionId = result.id;
        this.exam = result;
        // FIXME TER CUIDADO POIS NAO TENHO ACERTEZA DISTO!
        this.getExam( this.examId);
      },
      error => {
        console.log(error);
      }
    );
  }

  public getAllQuestions(questionsAll): void {
    this.questions = [];
    for ( let quest of questionsAll) {
      if( quest.answer) {
        this.choiseanswersId.push(quest.answer.id);
      }else {
        this.choiseanswersId.push(-1);
      }
      this.questions.push(quest.question);
    }
  }

  public saveEverything(): void {
    this.saveAll = !this.saveAll;
  }

}
