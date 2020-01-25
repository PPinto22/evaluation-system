import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GroupService} from '../../../../../services/group.service';
import {StudentsService} from '../../../../../services/students.service';
import {Exception} from '../../../../../execption/exception';
import {User} from '../../../../../models/user';
import {StudentsFilter} from '../../../../../filters/students_filter';
import {BreadCrumbService} from '../../../../../services/breadcrumb.service';
import {NavbarService} from "../../../../../services/navbar.service";

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
  private allStudentsOfGroup: string[];
  private model: any = {};
  private nameGroupValid: boolean;

  constructor(private group: GroupService,
              private router: Router,
              private route: ActivatedRoute,
              private students: StudentsService,
              private exception: Exception,
              private breadCrumb: BreadCrumbService,
              private navbarService: NavbarService
  ) {
    this.route.parent.params.subscribe(params => {
      this.classId = +params['class_id'];
    });
    this.allStudentsOfGroup = [];
    this.breadCrumb.setBreadCrum(['Class > Group > New']);
  }

  ngOnInit() {
    // this.cleanAll(); limpar tudo depois talvez
    // TODO talvez seja preciso mudar isto, depende como ficar, caso ele selecione um grupo é preciso colocar a verdadeiro.
    this.haveCreateGroup = false;
    this.groupAlreadyExists = false;
    // this.groupId = 3;
    this.nameGroupValid = false;
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

  public saveGroup(): void {
    if (this.groupCreate.nameGroup) {
      this.nameGroupValid = false;
      this.createGroup();
    }else {
      this.nameGroupValid = true;
    }
  }

  public createGroup(): void {
    this.group.createGroupByClasse(this.classId, this.groupCreate.nameGroup).subscribe(
      resultado => {
        this.haveCreateGroup = true;
        this.groupId = resultado.id;
        this.navbarService.sendUpdate(true);
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

  public addStudents() {
    this.students.postStudentByGroup(this.groupId, this.allStudentsOfGroup).subscribe(
      resultado => {
        this.router.navigate(['/dashboard', 'classes', this.classId, 'groups', this.groupId]);
      },
      error => {
        console.log(error);
        this.router.navigate(['/dashboard', 'classes', this.classId, 'groups', this.groupId]);
      }
    );
  }
  public treatmentEmail(): void {
    if (this.groupCreate.students) {
      this.wrongEmail = '';
      const separators = [',', ';', '\n'];
      const allNewStudents: string[] = [];
      for (const email of this.groupCreate.students.toString().split(new RegExp(separators.join('|')))) {
        if (!this.validateEmail(email)) {
          if (this.wrongEmail === '') {
            this.wrongEmail = email;
          } else {
            this.wrongEmail = this.wrongEmail + '\n' + email;
          }
        } else {
          this.allStudentsOfGroup.push(email);
        }
      }
      this.groupCreate.students = this.wrongEmail;
    }
  }

  public removeStudent(student: string): void {
    const idStudent = this.allStudentsOfGroup.indexOf(student);
    this.allStudentsOfGroup.splice(idStudent, 1);
  }

  public validateEmail(email: any): boolean {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }

}
