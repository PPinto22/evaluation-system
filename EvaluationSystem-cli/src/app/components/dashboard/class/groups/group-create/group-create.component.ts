import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GroupService} from '../../../../../services/group.service';
import {StudentsService} from '../../../../../services/students.service';
import {Exception} from '../../../../../execption/exception';
import {User} from '../../../../../models/user';
import {StudentsFilter} from '../../../../../filters/students_filter';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-group-create',
  templateUrl: './group-create.component.html',
  styleUrls: ['./group-create.component.css']
})
export class GroupCreateComponent implements OnInit, AfterViewInit {

  private classId: number;
  private groupId: number;
  private groupCreate: any = {};
  private haveCreateGroup: boolean;
  private wrongEmail = '';
  private groupAlreadyExists = false;
  private allStudentsOfGroup: Array<any>;
  private model: any = {};

  constructor(private group: GroupService,
              private router: Router,
              private route: ActivatedRoute,
              private students: StudentsService,
              private exception: Exception
  ) {
    this.route.params.subscribe(params => {
      this.classId = +params['class_id'];
    });
    this.allStudentsOfGroup = new Array<User>();
  }

  private getAllGroupStudents(): void {
    this.allStudentsOfGroup = new Array<User>();
    this.students.getUserByGroupId(this.groupId).subscribe(
      resultado => {
        for ( const student of resultado) {
          const p = { id: student.user.id, email: student.user.email, active: student.user.active, accepted: student.accepted};
          this.allStudentsOfGroup.push(p);
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  ngOnInit() {
    // TODO talvez seja preciso mudar isto, depende como ficar, caso ele selecione um grupo é preciso colocar a verdadeiro.
    this.haveCreateGroup = false;
    this.groupAlreadyExists = false;
    this.groupId = 3;
    this.getAllGroupStudents();
  }

  ngAfterViewInit(): void {
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

  public refreshInvitedSudents(): void {
    this.model.search = '';
    this.getAllGroupStudents();
  }

  public saveGroup(): void {

    this.router.navigate(['/dashboard', 'classes', this.classId]);

  }

  public createGroup(): void {
    this.group.createGroupByClasse(this.classId, this.groupCreate.nameGroup).subscribe(
      resultado => {
        this.haveCreateGroup = true;
        this.groupId = resultado.id;
        this.addStudents();
      },
      error => {
        if ( error.status === 406 ) {
          this.groupAlreadyExists = this.exception.errorHandlingCreateGroup(error);
        }
        console.log(error);
      }
    );
  }

  public create(): void {
    if (!this.haveCreateGroup) {
       this.createGroup();
    }else {
      if (this.groupCreate.students) {
        this.addStudents();
      }
    }
  }

  public addStudents() {
    this.students.postStudentByGroup(this.groupId, this.treatmentEmail()).subscribe(
      resultado => {
        this.getAllGroupStudents();
        this.groupCreate.students = this.wrongEmail;
        this.wrongEmail = '';
        console.log(resultado);
      },
      error => {
        console.log(error);
      }
    );
  }
  public treatmentEmail(): string[] {
    const separators = [',', ';', '\n'];
    const nameStudents: string[] = this.groupCreate.students.toString().split(new RegExp(separators.join('|')));
    for ( const email of nameStudents ) {
      if ( !this.validateEmail(email) ) {
        if ( this.wrongEmail === '') {
          this.wrongEmail = email;
          const index = nameStudents.indexOf(email);
          nameStudents.splice(index, 1);
        }else {
          this.wrongEmail = this.wrongEmail + '\n' + email;
          const index = nameStudents.indexOf(email);
          nameStudents.splice(index, 1);
        }
      }
    }
    return nameStudents;
  }

  public validateEmail(email: any): boolean {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }

}
