import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  constructor(
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {
  }

  public isTeacher(): boolean {
    return this.authentication.isTeacher();
  }

  public isStudent(): boolean {
    return this.authentication.isStudent();
  }
}
