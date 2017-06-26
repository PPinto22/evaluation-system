import {Component, OnInit, AfterViewInit, Output, EventEmitter} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';
import {ClassesService} from '../../../services/classes.service';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit, AfterViewInit {

  private new_class: boolean;
  private new_class_text: string;

  constructor(
    private authentication: AuthenticationService,
    private breadCrumb: BreadCrumbService,
    private classes: ClassesService
  ) { }

  ngOnInit() {this.breadCrumb.setBreadCrum(['DashBoard']);
    this.new_class = false;
    this.new_class_text = 'New Class';
  }

  newClass(){
    this.new_class = true;
    this.new_class_text = this.new_class ? 'New Class' : 'Save Class';
  }




  ngAfterViewInit() {
    x_navigation();
    page_content_onresize();
    this.scroll();
  }

  private scroll(): void {
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
    this.classes.createClasseByTeacher(this.authentication.getUserId(), this.new_class_text, 'AA');
  }

  private isTeacher(): boolean {
    return this.authentication.isTeacher();
  }
}
