import {AfterViewInit, Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ClassesService} from '../../../services/classes.service';
import {Class} from '../../../models/class';
import {User} from '../../../models/user';
import {Exception} from "../../../execption/exception";

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;


@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css']
})
export class ClassComponent implements OnInit, AfterViewInit {

  classId: number;
  classInformation: Class;
  teacherClass: User;

  constructor(
    private authentication: AuthenticationService,
    private breadCrumbService: BreadCrumbService,
    private route: ActivatedRoute,
    private _class: ClassesService,
    private exception: Exception
  ) {
    this.classInformation = new Class('', '');
    this.teacherClass = new User(-1, '', '', '', '', '');
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.classId = +params['class_id'];
      this.getClassInformation();
    });
  }

  ngAfterViewInit() {
    x_navigation();
    page_content_onresize();
  }

  private setBreadCrumb(): void {
    this.breadCrumbService.setBreadCrum(['Classes', this.classInformation.name]);
  }


  public isTeacher(): boolean {
    return this.authentication.isTeacher();
  }

  public getClassInformation(): void {
    this._class.getById(this.classId).subscribe(
      resultado => {
        this.createClass(resultado);
        this.setBreadCrumb();
      },
      error => {
        this.exception.errorHandlingInvalidToken(error);
        console.log(error);
      }
    );
  }

  public createClass(resultado): void {
    const teacher = resultado.teacher;
    this.teacherClass =  new User( teacher.id, teacher.email, teacher.firstName, teacher.lastName, teacher.type, '');
    const _classe = new Class( resultado.name, resultado.abbreviation);
    this.classInformation = _classe;
  }
}
