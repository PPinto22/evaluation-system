import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {CalendarComponent} from 'ap-angular2-fullcalendar';
import {BreadCrumbService} from '../../../services/breadcrumb.service';
import {observable} from 'rxjs/symbol/observable';
import {Observable} from 'rxjs/Observable';
import {ExamsService} from '../../../services/exams.service';
import {AuthenticationService} from '../../../services/authentication.service';
import {Exam} from '../../../models/exam';
import {User} from '../../../models/user';
import {Class} from '../../../models/class';
import {Group} from '../../../models/group';

declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit, AfterViewInit {

  @ViewChild(CalendarComponent) myCalendar: CalendarComponent;

  public calendarOptions: any = {
    // height: 'parent',
    fixedWeekCount : false,
    defaultDate: '2016-09-12',
    editable: false,
    eventLimit: true, // allow "more" link when too many events
    events: [
    ]
  };

  changeCalendarView(view) {
    this.myCalendar.fullCalendar('changeView', view);
  }


  constructor(private breadCrumb: BreadCrumbService,
              private exams: ExamsService,
              private authentication: AuthenticationService) { }


  ngOnInit() {
    this.putDateNowOnEvent();
    this.breadCrumb.setBreadCrum(['Schedule']);
    this.getAllExams();
  }

  ngAfterViewInit(): void {
    x_navigation();
    page_content_onresize();
  }

  private putDateNowOnEvent(): void {
    const date = new Date();
    if ( date.getUTCMonth() < 9) {
      if ( date.getUTCDate() < 10) {
        this.calendarOptions.defaultDate = date.getFullYear() + '-0' + (date.getUTCMonth() + 1)  + '-0' + date.getUTCDate();
      }else {
        this.calendarOptions.defaultDate = date.getFullYear() + '-0' + (date.getUTCMonth() + 1) + '-' + date.getUTCDate();
      }
    }else {
      this.calendarOptions.defaultDate = date.getFullYear() + '-' + (date.getUTCMonth() + 1) + '-' + date.getUTCDate();
    }
    console.log(this.calendarOptions.defaultDate);
  }

  private getAllExams(): void {
    this.exams.getExamsByUserId( this.authentication.getUserId() ).subscribe(
      resultado => {
        if (resultado.exams.History) {
          this.getExams(resultado.exams.History);
        }
        if (resultado.exams.Ongoing) {
          this.getExams(resultado.exams.Ongoing);
        }
        if (resultado.exams.Upcoming) {
          this.getExams(resultado.exams.Upcoming);
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  private getExams(exams): void {
    for ( const exam of exams){
      this.calendarOptions.events.push(this.createDateExam(exam));
    }
  }

  private createDateExam(exam): Exam {
    const calendarExam: any = { start: this.createDate(exam, 0), end: this.createDate(exam, exam.duration), title: exam.group._class.name};
    return calendarExam;
  }

  private createDate(exam, duration: number): string {
    const dateExam: Date = new Date( exam.beginDate + (duration * 60 * 1000) );
    const day =  dateExam.getDate();
    const month = dateExam.getUTCMonth() + 1;
    let startday = '' + day;
    let startmonth = '' + month;
    if ( day < 10 ) {
      startday = '0' + day;
    }
    if ( month < 10 ) {
      startmonth = '0' + month;
    }
    const startyear = dateExam.getFullYear();
    return startyear + '-' + startmonth + '-' + startday;
  }


}
