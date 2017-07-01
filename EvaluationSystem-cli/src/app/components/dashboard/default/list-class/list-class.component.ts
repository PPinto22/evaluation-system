import {AfterViewInit, Component, OnInit} from '@angular/core';
import {GroupService} from '../../../../services/group.service';
import {AuthenticationService} from '../../../../services/authentication.service';
import {Group} from '../../../../models/group';
import {Class} from '../../../../models/class';
import {User} from '../../../../models/user';
import {Exception} from '../../../../execption/exception';

declare var onReady: any;


@Component({
  selector: 'app-list-class',
  templateUrl: './list-class.component.html',
  styleUrls: ['./list-class.component.css']
})
export class ListClassComponent implements OnInit, AfterViewInit {

  allGroups: Array<Group>;

  constructor(private groupsService: GroupService,
              private authentication: AuthenticationService,
              private exception: Exception
  ) {
    this.allGroups = new Array<Group>();
  }

  ngOnInit() {
    this.getAllGroups();
  }

  ngAfterViewInit() {
    // onReady();
  }

  private getAllGroups() {
    this.groupsService.getGroupByUser(this.authentication.getUserId()).subscribe(
      resultado => {
        for ( const group of resultado){
          this.allGroups.push(this.createGroup(group));
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  private createGroup(groupT): Group {
    const teacher = groupT._class.teacher;
    const _teacher: User =  new User( teacher.id, teacher.email, teacher.firstName, teacher.lastName, teacher.type, '');
    const classe = groupT._class;
    const _classe = new Class( classe.name, classe.abbreviation);
    _classe.id = classe.id;
    _classe.user = _teacher;
    const group = groupT;
    const _group = new Group(group.name);
    _group.id = group.id;
    _group.class = _classe;
    return _group;
  }

  protected deleteGroup( group_id: number ): void {
    this.groupsService.deleteGroupById( group_id ).subscribe(
      result => {
        this.allGroups = this.allGroups.filter( obj => obj.id !== group_id );
      },
      error => {
        console.log(error);
        this.exception.errorHandlingInvalidToken(error);
      }
    );
    // TODO fazer delete do group
  }

  protected  editGroup( group_id: number ): void {
    // TODO fazer edit do group
  }

}
