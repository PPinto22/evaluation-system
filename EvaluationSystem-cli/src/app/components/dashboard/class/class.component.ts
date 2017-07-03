import {AfterViewInit, Component, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ClassesService} from '../../../services/classes.service';
import {Class} from '../../../models/class';
import {User} from '../../../models/user';
import {Exception} from "../../../execption/exception";
import {Popup} from 'ng2-opd-popup';
import {NavbarService} from '../../../services/navbar.service';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;


@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css']
})
export class ClassComponent implements OnInit, AfterViewInit {

  @ViewChild('popup2') popup2: Popup;

  classId: number;
  classInformation: Class;
  teacherClass: User;

  constructor(
    private authentication: AuthenticationService,
    private breadCrumbService: BreadCrumbService,
    private route: ActivatedRoute,
    private _class: ClassesService,
    private exception: Exception,
    private router: Router,
    private navbar: NavbarService
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

  clickRemoveClass(): void {
    this.popup2.options = {
      header: "" ,
      color: "red", // red, blue....
      widthProsentage: 40, // The with of the popou measured by browser width
      animationDuration: 1, // in seconds, 0 = no animation
      showButtons: true, // You can hide this in case you want to use custom buttons
      confirmBtnContent: "OK", // The text on your confirm button
      cancleBtnContent: "Cancel", // the text on your cancel button
      confirmBtnClass: "btn btn-default", // your class for styling the confirm button
      cancleBtnClass: "btn btn-default", // you class for styling the cancel button
      animation: "fadeInDown" // 'fadeInLeft', 'fadeInRight', 'fadeInUp', 'bounceIn','bounceInDown'
    };

    this.popup2.show(this.popup2.options);
  }

  private cancelDeleteClass(): void {
  }

  private confirmDeleteClass(): void {
    this.removeClass();
  }

  private removeClass (): void {
    this._class.deleteClass( this.classId).subscribe(
      result => {
        this.navbar.sendUpdate(true);
        this.router.navigate(['/dashboard']);
      },
      error => {
        console.log(error);
      }
    );
  }
}
