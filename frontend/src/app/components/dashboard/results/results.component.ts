import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';
import {Exam} from '../../../models/exam';
import {Group} from '../../../models/group';
import {ScoresService} from '../../../services/scores.service';
import {Class} from '../../../models/class';
import {User} from '../../../models/user';
import {Submission} from '../../../models/submission';

declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit, AfterViewInit {

  private submissionResults: Submission[];

  constructor(
    private authentication: AuthenticationService,
    private breadCrumbService: BreadCrumbService,
    private scoresService: ScoresService
  ) { }

  ngOnInit() {
    this.submissionResults = [];
    this.getResults();
    this.setBreadCrumb();
  }

  ngAfterViewInit(): void {
    this.rezise();
  }

  private rezise(): void {
    x_navigation();
    page_content_onresize();
  }

  private setBreadCrumb(): void {
    this.breadCrumbService.setBreadCrum(['Results']);
  }

  public isTeacher(): boolean {
    return this.authentication.isTeacher();
  }

  public isStudent(): boolean {
    return this.authentication.isSudent();
  }

  public getResults(): void {
    this.scoresService.getUserScore(this.authentication.getUserId()).subscribe(
      result => {
        this.submissionResults = [];
        for (const group of result.groups) {
          const new_group: Group =  this.createGroup(group.group);
          for (const exam of group.exams) {
            const new_submission: Submission = this.createSubmission(exam.score);
            new_submission.exam = this.createExam(exam.exam);
            new_submission.exam.group = new_group;
            this.submissionResults.push( new_submission );
          }
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  private createGroup(group: any): Group {
    const new_group: Group = new Group(group.name);
    new_group.id = group.id;
    new_group.class = this.createClass(group._class);
    return new_group;
  }

  private createClass(class_r: any): Class {
    const new_class: Class = new Class(class_r.name, class_r.abbreviation);
    new_class.id = class_r.id;
    new_class.user = this.createUser(class_r.teacher);
    return new_class;
  }

  private createUser(user: any): User {
    const new_user: User = new User(user.id, user.email, user.firstName, user.lastName, user.type, '');
    return new_user;
  }

  private createExam(exam: any): Exam {
    const new_exam = new Exam(exam.name, exam.beginExam, exam.duration);
    new_exam.id = exam.id;
    return new_exam;
  }

  private createSubmission(submission: any): Submission {
    const new_submission = new Submission(submission.submissionID, submission.score);
    return new_submission;
  }

  public refresResults(): void {
    this.getResults();
  }

}
