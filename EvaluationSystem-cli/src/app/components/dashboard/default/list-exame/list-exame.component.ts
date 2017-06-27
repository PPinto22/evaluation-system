import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ClassesService} from '../../../../services/classes.service';
import {AuthenticationService} from '../../../../services/authentication.service';
import {ExamsService} from '../../../../services/exams.service';
import {raceStatic} from 'rxjs/operator/race';
import {Exam} from '../../../../models/exam';
import {User} from '../../../../models/user';
import {Class} from '../../../../models/class';
import {Group} from '../../../../models/group';

declare var panels: any;

@Component({
  selector: 'app-list-exame',
  templateUrl: './list-exame.component.html',
  styleUrls: ['./list-exame.component.css']
})
export class ListExameComponent implements OnInit, AfterViewInit {

  upComingExams: Array<Exam>;
  historyExams: Array<Exam>;

  constructor(
    private authentication: AuthenticationService,
    private exams: ExamsService
    ) {
    this.upComingExams = new Array<Exam>();
    this.historyExams = new Array<Exam>();
  }

  ngOnInit() {
    this.getHistoryAndUpComming();
  }

  ngAfterViewInit() {
    panels();
  }

  private getHistoryAndUpComming(): void {
    this.exams.getExamsByUserId( this.authentication.getUserId() ).subscribe(
      resultado => {
        for ( const exams of resultado.exams ) {
          if (exams.History) {
            this.getAllHistory(exams);
          }
          if (exams.Ongoing || exams.Upcoming) {
            this.getAllComing(exams);
          }
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  private getAllHistory (exams): void {
    for ( const exam of exams){
      this.historyExams.push(this.createExam(exam));
    }
  }

  private getAllComing (exams): void {
    for ( const exam of exams){
      this.upComingExams.push(this.createExam(exam));
    }
  }

  private createExam(exam): Exam {
    const teacher = exam.group._class.teacher;
    const _teacher =  new User( teacher.id, teacher.email, teacher.firstName, teacher.lastName, teacher.type, '');
    const classe = exam.group._class;
    const _classe = new Class( classe.id, classe.name, classe.abbreviation, _teacher);
    const group = exam.group;
    const _group = new Group( group.id, group.name, _classe );
    return new Exam( exam.id, exam.name, exam.beginDate, exam.duration, _group);
  }

  private refreshUpComing(): void {
    this.getHistoryAndUpComming();
  }

  private refreshHistory(): void {
    this.getHistoryAndUpComming();
  }

}
