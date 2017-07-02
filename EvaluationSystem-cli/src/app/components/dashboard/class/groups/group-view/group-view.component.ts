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
import {Exam} from "../../../../../models/exam";
import {ExamsService} from "../../../../../services/exams.service";
import {User} from "../../../../../models/user";


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
  private model: any = {}

  private upComingExams: Exam[];
  private onGoingExams: Exam[];
  private historyExams: Exam[];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authentication: AuthenticationService,
    private students: StudentsService,
    private groupsService: GroupService,
    private breadCrumbService: BreadCrumbService,
    private exception: Exception,
    private examsService: ExamsService
  ) {
    this.allStudentsOfGroup = new Array<any>();
  }

  ngOnInit() {
    this.upComingExams = [];
    this.onGoingExams = [];
    this.historyExams = [];
    this.order_date = true;
    this.order_date_text = 'most recent frist';
    this.route.parent.params.subscribe( parent_params => {
      this.classId = +parent_params['class_id'];

      this.route.params.subscribe( params => {
        this.groupId = +params['group_id'];
        console.log(this.classId + '->' + this.groupId);
        this.getGroup();
        this.getAllStudentsOfGroup();
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
        // FIXME isto não devia ser feito aqui mas por causa dos nulls está aqui
        this.getAllExamsOfGroup(this.groupId);
      },
      error => {
        // this.exception.errorHandlingInvalidToken(error);
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
    newClass.user = this.createUser(class_r.teacher);
    return newClass;
  }

  private createUser(user: any): User {
    const new_user: User = new User(user.id, user.email, user.firstName, user.lastName, user.type, '');
    return new_user;
  }


  private getAllStudentsOfGroup(): void {
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

// <tr id="trow_1" [routerLink]="['/dashboard','classes','1','groups','1','exams','1']">
// <tr id="trow_2" [routerLink]="['/dashboard','classes','1','groups','1','exams','1','submit']">
// <tr id="trow_3" [routerLink]="['/dashboard','classes','1','groups','1','exams','1','submission','1']">
// <tr id="trow_4" [routerLink]="['/dashboard','classes','1','groups','1','exams','1','results']">

  public getAllExamsOfGroup( group_id: number): void {
    this.examsService.getExamsByGroupId(group_id).subscribe(
      result => {
        this.upComingExams = [];
        this.onGoingExams = [];
        this.historyExams = [];
        console.log(result);
        if (result.exams.History) {
          this.getAllHistory(result.exams.History);
        }
        if (result.exams.Ongoing) {
          this.getAllOngoing(result.exams.Ongoing);
        }
        if (result.exams.Upcoming) {
          this.getAllUpcoming(result.exams.Upcoming);
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

  private getAllUpcoming (exams): void {
    for (const exam of exams) {
      this.upComingExams.push(this.createExam(exam));
    }
  }

  private createExam(exam): Exam {
    const examnew = new Exam( exam.name, exam.beginDate, exam.duration);
    examnew.id = exam.id;
    examnew.group = this.group;
    return examnew;
  }

  public goToExamResult( exam: Exam): void {
    if (this.isTeacher()) {
      this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id, 'results']);
    } else {
      this.examsService.getSubmissionsByExam(exam.id, this.authentication.getUserId()).subscribe(
        result => {
          console.log('submission by exam_id');
          console.log(result);
          if (result && result[0]) {
            this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id, 'submission', result[0].id]);
          }
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  public goToExamOnGoing(exam: Exam): void {
    if (this.isTeacher()) {
      this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id]);
    } else {
      this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id, 'submit']);
    }
  }
}
