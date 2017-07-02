import { Component, OnInit } from '@angular/core';
import {Submission} from '../../../../../models/submission';
import {BreadCrumbService} from '../../../../../services/breadcrumb.service';
import {AuthenticationService} from '../../../../../services/authentication.service';
import {ScoresService} from '../../../../../services/scores.service';
import {User} from '../../../../../models/user';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-exam-result-all',
  templateUrl: './exam-result-all.component.html',
  styleUrls: ['./exam-result-all.component.css']
})
export class ExamResultAllComponent implements OnInit {

  private class_id: number;
  private group_id: number;
  private exam_id: number;
  private submissionResults: Submission[];

  constructor(
    private route: ActivatedRoute,
    private authentication: AuthenticationService,
    private breadCrumbService: BreadCrumbService,
    private scoresService: ScoresService
  ) { }

  ngOnInit() {
    this.route.params.subscribe( params => {
      this.class_id = +params['class_id'];
      this.group_id = +params['group_id'];
      this.exam_id = +params['exam_id'];

      this.getExamResults(this.exam_id);
    });
  }

  public getExamResults(exam_id: number): void {
    this.scoresService.getExamScore( exam_id ).subscribe(
      result => {
        console.log('results all');
        console.log(result);
        this.submissionResults = [];
        for (const student of result.students) {
          const new_submission: Submission = this.createSubmission(student.score);
          new_submission.user = this.createUser(student.student);
          this.submissionResults.push( new_submission );
        }
        console.log(this.submissionResults);
      },
      error => {
        console.log(error);
      }
    );
  }

  private createUser(user: any): User {
    const new_user: User = new User(user.id, user.email, user.firstName, user.lastName, user.type, '');
    new_user.active = user.active;
    return new_user;
  }

  private createSubmission(score: any): Submission {
    const new_submission = new Submission(score.submissionID, score.score);
    new_submission.correct = score.correct;
    new_submission.total = score.total;
    return new_submission;
  }

  public refreshExamResult(): void {
    this.getExamResults(this.exam_id);
  }
}
