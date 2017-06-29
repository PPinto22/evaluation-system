import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../../../services/authentication.service';
import {ActivatedRoute, Router} from "@angular/router";
import {StudentsService} from '../../../../../services/students.service';
import {StudentsFilterGroup} from '../../../../../filters/students_filter_group';


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
  private groupId: number;
  private allStudentsOfGroup: Array<any>;
  private model: any = {};

  constructor(
    private router: ActivatedRoute,
    private authentication: AuthenticationService,
    private students: StudentsService
  ) {
    this.allStudentsOfGroup = new Array<any>();
  }

  ngOnInit() {
    this.order_date = true;
    this.order_date_text = 'most recent frist';
    this.router.params.subscribe( params => {
      this.groupId = params['group_id'];
      this.groupId = 2; // TODO tirar esta merda depois!
    });
    this.getAllGroupStudents();
  }

  ngAfterViewInit(): void {
    panels();
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

  public toggleOrderDate(): void {
    this.order_date = !this.order_date;
    this.order_date_text = this.order_date ? 'most recent frist' : 'oldest frist';
    // TODO fazer função para mudar a ordem dos exames
  }

  private isTeacher(): boolean {
    return this.authentication.isTeacher();
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




}
