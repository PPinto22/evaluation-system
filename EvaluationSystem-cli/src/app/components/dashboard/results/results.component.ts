import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';
import {Exam} from '../../../models/exam';
import {Group} from '../../../models/group';
import {ScoresService} from '../../../services/scores.service';
import {Class} from '../../../models/class';
import {User} from '../../../models/user';
import {Submission} from '../../../models/submission';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  private submissionResults: Submission[];

  constructor(
    private authentication: AuthenticationService,
    private breadCrumbService: BreadCrumbService,
    private scoresService: ScoresService
  ) { }

  ngOnInit() {
    this.submissionResults = [];
    this.setBreadCrumb();
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

// {
//   "groups": [
//     {
//       "group": {
//         "id": 1,
//         "name": "Name1",
//         "_class": {
//           "name": "Name1",
//           "abbreviation": "Abbreviation1",
//           "teacher": {
//             "..."
//           },
//           "id": 1
//         }
//       },
//       "exams": [
//         {
//           "exam": {
//             "id": 1,
//             "name": "Exam 8",
//             "beginDate": 1498908600000,
//             "duration": 60
//           },
//           "score": {
//             "submissionID": 1,
//             "score": 0
//           }
//         }
//         ]
//     }
//     ]
// }

  public getResults(): void {
    this.scoresService.getUserScore(this.authentication.getUserId()).subscribe(
      result => {
        console.log(result);
        for (const group of result.groups) {
          const new_group: Group =  this.createGroup(group.group);
          for (const exam of group.exams) {
            const new_submission: Submission = this.createSubmission(exam.score);
            new_submission.exam = this.createExam(exam.exam);
            new_submission.exam.group = new_group;
            this.submissionResults.push( new_submission );
          }
        }
        console.log(this.submissionResults);
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
    new_class.user = this.createUser(class_r.user);
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
    const new_submission = new Submission(submission.id, submission.score);
    return new_submission;
  }

  public refresResults(): void {
    // TODO fazer o refhres do exames
  }
}
