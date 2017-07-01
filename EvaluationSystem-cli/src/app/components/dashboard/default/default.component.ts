import {Component, OnInit, AfterViewInit, Output, EventEmitter, OnChanges, SimpleChanges} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';
import {ClassesService} from '../../../services/classes.service';
import {Exception} from '../../../execption/exception';
import {Router} from "@angular/router";

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;
declare var onReady: any;

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit, AfterViewInit, OnChanges {

  private classAlreadyExists: boolean;
  private new_class_add: any = {};
  private classAsCreate: boolean;


  constructor(
    private authentication: AuthenticationService,
    private breadCrumb: BreadCrumbService,
    private classes: ClassesService,
    private exception: Exception,
    private router: Router
  ) { }

  ngOnInit() {
    this.classAlreadyExists = false;
    this.classAsCreate = false;
    this.breadCrumb.setBreadCrum(['Dashboard']);
  }

  ngOnChanges(changes: SimpleChanges): void {
  }


  ngAfterViewInit() {
    console.log('ngAfterViewInit Default')
    // onReady();
    // x_navigation();
    // page_content_onresize();
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
    this.classAsCreate = false;
    this.classes.createClasseByUser(this.authentication.getUserId(), this.new_class_add.nameClass, this.new_class_add.abbrev).subscribe(
      resultado => {
        this.classAsCreate = true;
        this.new_class_add = {};
        console.log(resultado);
        this.router.navigate(['/dashboard', 'classes', resultado.id]);
      },
      error => {
        this.exception.errorHandlingInvalidToken(error);
        if ( error.status === 406) {
          this.classAlreadyExists = this.exception.errorHandlingCreateClass(error);
        }
        this.classAsCreate = false;
        console.log(error);
      }
    );
  }

  public changeAlreadyExists(): void {
    this.classAlreadyExists = false;
  }

  private isTeacher(): boolean {
    return this.authentication.isTeacher();
  }
}
