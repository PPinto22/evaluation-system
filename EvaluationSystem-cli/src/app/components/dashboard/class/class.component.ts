import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {BreadCrumbService} from '../../../services/breadcrumb.service';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;


@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css']
})
export class ClassComponent implements OnInit, AfterViewInit {

  constructor(
    private authentication: AuthenticationService,
    private breadCrumb: BreadCrumbService
  ) { }

  ngOnInit() {
    this.breadCrumb.setBreadCrum(['Classes']);
  }

  ngAfterViewInit() {
    x_navigation();
    page_content_onresize();
  }

  public isTeacher(): boolean {
    return this.authentication.isTeacher();
  }
}
