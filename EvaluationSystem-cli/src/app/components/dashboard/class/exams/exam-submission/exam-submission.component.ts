import { Component, OnInit } from '@angular/core';
import {Question} from '../../../../../models/question';
import {ExamsService} from '../../../../../services/exams.service';
import {ActivatedRoute} from '@angular/router';
import {Exam} from '../../../../../models/exam';
import {BreadCrumbService} from "../../../../../services/breadcrumb.service";

@Component({
  selector: 'app-exam-submission',
  templateUrl: './exam-submission.component.html',
  styleUrls: ['./exam-submission.component.css']
})
export class ExamSubmissionComponent implements OnInit {

  private examId: number;
  private exam: Exam;
  private questions: Question[];

  constructor(
    private breadCrumb: BreadCrumbService,
    private examsService: ExamsService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    // para teste
    this.questions = [];
    const question1 = new Question(1, 'spring');
    question1.id = 1;
    const question2 = new Question(2, 'angular');
    question2.id = 1;
    this.questions.push( question1 );
    this.questions.push( question2);

    this.route.params.subscribe( params => {
      this.examId = +params['exam_id'];
      console.log('Exam id: ' + this.examId);
      this.getExam( this.examId );
      this.breadCrumb.setBreadCrum(['Exam OnGoing']); // FIXME alterações
    });

  }

  private getExam( exam_id: number ): void {
    this.examsService.getExamById(exam_id).subscribe(
      result => {
        console.log(result);
      },
      error => {
        console.log(error);
      }
    );
  }

}
