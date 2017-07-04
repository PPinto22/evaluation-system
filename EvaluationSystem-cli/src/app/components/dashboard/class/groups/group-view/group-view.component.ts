import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {AuthenticationService} from '../../../../../services/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';
import {StudentsService} from '../../../../../services/students.service';
import {StudentsFilterGroup} from '../../../../../filters/students_filter_group';
import {BreadCrumbService} from '../../../../../services/breadcrumb.service';
import {GroupService} from '../../../../../services/group.service';
import {Exception} from '../../../../../execption/exception';
import {Group} from '../../../../../models/group';
import {Class} from '../../../../../models/class';
import {Exam} from '../../../../../models/exam';
import {ExamsService} from '../../../../../services/exams.service';
import {User} from '../../../../../models/user';
import {NavbarService} from '../../../../../services/navbar.service';
import {Popup} from 'ng2-opd-popup';


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
    private examsService: ExamsService,
    private navbarService: NavbarService,
    private popup: Popup
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

  ClickButton(): void {
    this.popup.options = {
      header: '' ,
      color: 'red', // red, blue....
      widthProsentage: 40, // The with of the popou measured by browser width
      animationDuration: 1, // in seconds, 0 = no animation
      showButtons: true, // You can hide this in case you want to use custom buttons
      confirmBtnContent: 'OK', // The text on your confirm button
      cancleBtnContent: 'Cancel', // the text on your cancel button
      confirmBtnClass: 'btn btn-default', // your class for styling the confirm button
      cancleBtnClass: 'btn btn-default', // you class for styling the cancel button
      animation: 'fadeInDown' // 'fadeInLeft', 'fadeInRight', 'fadeInUp', 'bounceIn','bounceInDown'
    };

    this.popup.show(this.popup.options);
  }

  private cancelDelete(): void {
  }

  private confirmDelete(): void {
    this.removeGroup();
  }

  private setBreadCrumb(): void {
    // FIXME adiconar o nome do grupo
      this.breadCrumbService.setBreadCrum(['Class', this.getGroupName(), 'Group']);
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


  private removeStudent(id: number): void {
    this.students.deleteStudentById(this.groupId, id).subscribe(
      resultado => {
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
        // FIXME isto não devia ser feito aqui mas por causa dos nulls está aqui
        this.getAllExamsOfGroup(this.groupId);
      },
      error => {
        // this.exception.errorHandlingInvalidToken(error);
      }
    );
  }

  private getAllStudentsOfGroup(): void {
     this.allStudentsOfGroup = new Array<any>();
     this.students.getUserByGroupId(this.groupId).subscribe(
       resultado => {
         for ( const student of resultado) {
          const studen = { id: student.user.id, email: student.user.email, active: student.user.active, accepted: student.accepted};
          this.allStudentsOfGroup.push(studen);
         }
       },
       error => {
        console.log(error);
       }
     );
   }

  public getGroupName(): string {
    return this.group ? this.group.name : '';
  }

  public getAllExamsOfGroup( group_id: number): void {
    this.examsService.getExamsByGroupId(group_id).subscribe(
      result => {
        this.upComingExams = [];
        this.onGoingExams = [];
        this.historyExams = [];
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
  private createExam(exam): Exam {
    const examnew = new Exam( exam.name, exam.beginDate, exam.duration);
    examnew.id = exam.id;
    examnew.group = this.group;
    return examnew;
  }

  private isTeacher(): boolean {
    return this.authentication.isTeacher();
  }

  public goToExamResult( exam: Exam): void {
    if (this.isTeacher()) {
      this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id, 'results']);
    } else {
      this.examsService.getSubmissionsByExam(exam.id, this.authentication.getUserId()).subscribe(
        result => {
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

  public removeGroup(): void {
    this.groupsService.deleteGroupById(this.groupId).subscribe(
      result => {
        this.navbarService.sendUpdate(true);
        this.router.navigate(['/dashboard']);
      },
      error => {
        console.log(error);
      }
    );
  }

  public addStudent() {
    if ( this.model.search && this.validateEmail(this.model.search) ) {
      const addStudentNow: string[] = [];
      addStudentNow.push(this.model.search);
      this.students.postStudentByGroup(this.groupId, addStudentNow).subscribe(
        resultado => {
          if (resultado[0].user) {
            const studen = { id: resultado[0].user.id, email: resultado[0].user.email, active: resultado[0].user.active, accepted: resultado[0].accepted};
            this.allStudentsOfGroup.push(studen);
          }
          this.model.search = '';
        },
        error => {
          console.log(error);
          this.model.search = '';
        }
      );
    }
  }

  public validateEmail(email: any): boolean {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }
}
