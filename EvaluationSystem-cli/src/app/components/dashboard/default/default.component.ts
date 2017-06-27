import {Component, OnInit, AfterViewInit, Output, EventEmitter} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';
import {ClassesService} from '../../../services/classes.service';
import {Exception} from '../../../execption/exception';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit, AfterViewInit {

  private classAlreadyExists: boolean;
  private new_class_add: any = {}

  constructor(
    private authentication: AuthenticationService,
    private breadCrumb: BreadCrumbService,
    private classes: ClassesService,
    private exception: Exception
  ) { }

  ngOnInit() {
    this.classAlreadyExists = false;
    this.breadCrumb.setBreadCrum(['DashBoard']);
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
    this.classes.createClasseByUser(this.authentication.getUserId(), this.new_class_add.abbrev, this.new_class_add.nameClass).subscribe(
      resultado => {
        console.log(resultado);
      },
      error => {
        if ( error.status === 406) {
          console.log(this.exception.errorHandlingCreateClass(error))
          this.classAlreadyExists = this.exception.errorHandlingCreateClass(error);
        }
        console.log(error);
      }
    );
  }

  private isTeacher(): boolean {
    return this.authentication.isTeacher();
  }
}
