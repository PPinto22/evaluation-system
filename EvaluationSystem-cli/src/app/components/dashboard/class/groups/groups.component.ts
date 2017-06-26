import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../../services/authentication.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit, AfterViewInit {


  constructor(
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {

  }

  private isTeacher(): boolean {
    return this.authentication.isTeacher();
  }


}
