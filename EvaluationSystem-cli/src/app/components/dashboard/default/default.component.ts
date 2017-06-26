import {Component, OnInit, AfterViewInit, Output, EventEmitter} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';
import {ClassesService} from '../../../services/classes.service';

declare var $: any;

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit, AfterViewInit {

  newClass: string;

  constructor(
    private authentication: AuthenticationService,
    private breadCrumb: BreadCrumbService,
    private classes: ClassesService
  ) { }

  ngOnInit() {
    this.breadCrumb.setBreadCrum(['DashBoard']);
  }

  ngAfterViewInit() {
    $('.scroll').mCustomScrollbar({
      axis: 'y',
      autoHideScrollbar: true,
      scrollInertia: 20,
      advanced: {
        autoScrollOnFocus: false
      }
    }, {passive: true});
  }

  public addClass(): void {
    // TODO altera a abreviatura
    this.classes.createClasseByTeacher(this.authentication.getUserId(), this.newClass, 'AA');
  }
  
  private isTeacher(): boolean {
    return this.authentication.isTeacher();
  }
}
