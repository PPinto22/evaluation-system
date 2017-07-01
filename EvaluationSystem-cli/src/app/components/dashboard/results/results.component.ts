import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  constructor(
    private authentication: AuthenticationService,
    private breadCrumbService: BreadCrumbService
  ) { }

  ngOnInit() {
    this.setBreadCrumb();
  }

  private setBreadCrumb(): void {
    this.breadCrumbService.setBreadCrum(['Results']);
  }

  public isTeacher(): boolean {
    return this.authentication.isTeacher();
  }

  public isStudent(): boolean {
    return this.authentication.isSudent();
  }

  public refresResults(): void {
    // TODO fazer o refhres do exames
  }
}
