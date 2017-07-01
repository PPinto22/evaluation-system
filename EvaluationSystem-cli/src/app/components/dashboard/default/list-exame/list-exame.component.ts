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

  private upComingExams: Array<Exam>;
  private historyExams: Array<Exam>;
  private onGoingExams: Array<Exam>;

  constructor(
    private authentication: AuthenticationService,
    private exams: ExamsService
    ) {
    this.cleanAllArray();
  }

  ngOnInit() {
    this.getHistoryAndUpComming();
  }

  ngAfterViewInit() {
    panels();
  }

  private cleanAllArray(): void {
    this.upComingExams = new Array<Exam>();
    this.historyExams = new Array<Exam>();
    this.onGoingExams = new Array<Exam>();
  }

  private getHistoryAndUpComming(): void {
    this.cleanAllArray();
    this.exams.getExamsByUserId( this.authentication.getUserId() ).subscribe(
      resultado => {
        if (resultado.exams.History) {
          this.getAllHistory(resultado.exams.History);
        }
        if (resultado.exams.Upcoming) {
          this.getAllComing(resultado.exams.Upcoming);
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

  private getAllOngoing (exams): void {
    for (const exam of exams) {
      this.onGoingExams.push(this.createExam(exam));
    }
  }

  private getAllComing (exams): void {
    for (const exam of exams) {
      this.upComingExams.push(this.createExam(exam));
    }
  }

  private createExam(exam): Exam {
    const teacher = exam.group._class.teacher;
    const _teacher =  new User( teacher.id, teacher.email, teacher.firstName, teacher.lastName, teacher.type, '');
    const classe = exam.group._class;
    const _classe = new Class( classe.name, classe.abbreviation);
    _classe.id = classe.id;
    _classe.user = _teacher;
    const group = exam.group;
    const _group = new Group(group.name);
    _group.id = group.id;
    _group.class = _classe;
    const examnew = new Exam( exam.name, exam.beginDate, exam.duration);
    examnew.id = exam.id;
    examnew.group = _group;
    return examnew;
  }

  private refreshUpComing(): void {
    this.getHistoryAndUpComming();
  }

  private refreshHistory(): void {
    this.getHistoryAndUpComming();
  }

}
