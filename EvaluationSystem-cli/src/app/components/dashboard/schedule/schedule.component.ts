import {Component, OnInit, ViewChild} from '@angular/core';
import {CalendarComponent} from 'ap-angular2-fullcalendar';

declare var fullCalendar: any;

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  @ViewChild(CalendarComponent) myCalendar: CalendarComponent;

  public calendarOptions: Object = {
    // height: 'parent',
    fixedWeekCount : false,
    defaultDate: '2016-09-12',
    editable: true,
    eventLimit: true, // allow "more" link when too many events
    events: [
      {
        title: 'All Day Event',
        start: '2016-09-01'
      },
      {
        title: 'Long Event',
        start: '2016-09-07',
        end: '2016-09-10'
      },
      {
        title: 'Conference',
        start: '2016-09-11',
        end: '2016-09-13'
      }
    ]
  };

  changeCalendarView(view) {
    this.myCalendar.fullCalendar('changeView', view);
  }


  constructor() { }


  ngOnInit() {
    this.changeCalendarView(this.myCalendar);
  }

}
