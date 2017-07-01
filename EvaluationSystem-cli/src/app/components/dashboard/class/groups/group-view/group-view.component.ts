import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../../../services/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';
import {StudentsService} from '../../../../../services/students.service';
import {StudentsFilterGroup} from '../../../../../filters/students_filter_group';
import {BreadCrumbService} from '../../../../../services/breadcrumb.service';
import {GroupService} from '../../../../../services/group.service';
import {Exception} from '../../../../../execption/exception';
import {Group} from '../../../../../models/group';
import {Class} from '../../../../../models/class';


declare var $: any;
declare var panels: any;

@Component({
  selector: 'app-group-view',
  templateUrl: './group-view.component.html',
  styleUrls: ['./group-view.component.css']
})
export class GroupViewComponent implements OnInit, AfterViewInit  {

  private order_date: boolean; // toggle booblen true if most recent frist or false for oldest frist
  private order_date_text: string;

  private classId: number;
  private groupId: number;
  private group: Group;
  private allStudentsOfGroup: Array<any>;
  private model: any = {};

  constructor(
    private router: ActivatedRoute,
    private authentication: AuthenticationService,
    private students: StudentsService,
    private groupsService: GroupService,
    private breadCrumbService: BreadCrumbService,
    private exception: Exception
  ) {
    this.allStudentsOfGroup = new Array<any>();
  }

  ngOnInit() {
    this.order_date = true;
    this.order_date_text = 'most recent frist';
    this.router.parent.params.subscribe( parent_params => {
      this.classId = +parent_params['class_id'];

      this.router.params.subscribe( params => {
        this.groupId = +params['group_id'];
        console.log(this.classId + '->' + this.groupId);
        this.getGroup();
        this.getAllGroupStudents();
        this.setBreadCrumb();
      });

    });
  }

  ngAfterViewInit(): void {
    panels();
    this.scroll();
  }

  private setBreadCrumb(): void {
    // FIXME adiconar o nome do grupo
    this.breadCrumbService.setBreadCrum(['Class', , 'Group']);
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

  public toggleOrderDate(): void {
    this.order_date = !this.order_date;
    this.order_date_text = this.order_date ? 'most recent frist' : 'oldest frist';
    // TODO fazer função para mudar a ordem dos exames
  }

  private isTeacher(): boolean {
    return this.authentication.isTeacher();
  }

  private removeStudent(id: number): void {
    this.students.deleteStudentById(this.groupId, id).subscribe(
      resultado => {
        console.log(resultado);
        const student = this.allStudentsOfGroup.find( x => x.id === id);
        const studentIndex = this.allStudentsOfGroup.indexOf(student);
        this.allStudentsOfGroup.splice(studentIndex, 1);
      },
      error => {
        console.log(error);
      }
    );
  }

  private getGroup(): void {
    this.groupsService.getGroupById(this.groupId).subscribe(
      result => {
        this.group = this.createGroup(result);
        console.log(result);
      },
      error => {
        this.exception.errorHandlingInvalidToken(error);
      }
    );
  }

  private createGroup(group: any): Group {
    const new_group = new Group(group.name);
    new_group.id = group.id;
    new_group.class = this.createClass(group._class);
    return new_group;
  }

  private createClass(class_r: any): Class {
    const newClass = new Class(class_r.name, class_r.abbreviation);
    newClass.id = class_r.id;
    return newClass;
  }


  private getAllGroupStudents(): void {
     this.allStudentsOfGroup = new Array<any>();
     this.students.getUserByGroupId(this.groupId).subscribe(
       resultado => {
         for ( const student of resultado) {
          const studen = { id: student.user.id, email: student.user.email, active: student.user.active, accepted: student.accepted};
          this.allStudentsOfGroup.push(studen);
         }
         console.log(this.allStudentsOfGroup);
       },
       error => {
        console.log(error);
       }
     );
   }

  public getGroupName(): string {
    return this.group ? this.group.name : '';
  }


}
