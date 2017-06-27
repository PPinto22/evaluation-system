import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GroupService} from '../../../../../services/group.service';
import {StudentsService} from '../../../../../services/students.service';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-group-create',
  templateUrl: './group-create.component.html',
  styleUrls: ['./group-create.component.css']
})
export class GroupCreateComponent implements OnInit, AfterViewInit {
  classId: number;
  groupId: number;
  groupCreate: any = {};
  haveCreateGroup: boolean;
  wrongEmail = '';

  constructor(private group: GroupService,
              private router: Router,
              private route: ActivatedRoute,
              private students: StudentsService
  ) {
    this.route.params.subscribe(params => {
      this.classId = +params['id'];
    });
  }

  ngOnInit() {
    // TODO talvez seja preciso mudar isto, depende como ficar, caso ele selecione um grupo é preciso colocar a verdadeiro.
    this.haveCreateGroup = true;
    this.groupId = 2;
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
        console.log(error);
      }
    );
  }

  public create(): void {
    console.log('create');
    if (!this.haveCreateGroup) {
      this.createGroup();
    }
    if (this.groupCreate.students) {
      this.addStudents();
    }
  }

  public addStudents() {
    this.students.postStudentByGroup(this.groupId, this.treatmentEmail()).subscribe(
      resultado => {
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
