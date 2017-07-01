import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {ExamsService} from '../../../../../services/exams.service';
import {Question} from '../../../../../models/question';

@Component({
  selector: 'app-exam-result',
  templateUrl: './exam-result.component.html',
  styleUrls: ['./exam-result.component.css']
})
export class ExamResultComponent implements OnInit {

  private submissionId: number;
  private exam: any = {};
  private nameExam = '';
  private questions: Question[];
  private choiseanswersId: number [];

  constructor(
    private location: Location,
    private router: ActivatedRoute,
    private examsService: ExamsService
  ) { }

  ngOnInit() {
    this.choiseanswersId = [];
    this.router.params.subscribe( params => {
      this.submissionId = +params['submission_id'];
    });
    this.getExamSubmission();
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

  public getAllQuestions(questionsAll): void {
    this.questions = [];
    for ( let quest of questionsAll) {
      if ( quest.answer) {
        this.choiseanswersId.push(quest.answer.id);
      }else {
        this.choiseanswersId.push(-1);
      }
      console.log(quest);
      quest.question.dificulty = quest.question.difficulty;
      this.questions.push(quest.question);
    }
  }

  public goBack(): void {
    this.location.back();
  }
}
