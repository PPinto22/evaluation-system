import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;
declare var panels: any;


@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css']
})
export class ClassComponent implements OnInit, AfterViewInit {

  constructor(
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    x_navigation();
    page_content_onresize();
    panels();
  }

  public isTeacher(): boolean {
    return this.authentication.isTeacher();
  }
}
