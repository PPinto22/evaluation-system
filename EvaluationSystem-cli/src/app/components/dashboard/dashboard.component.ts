import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {Router} from '@angular/router';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, AfterViewInit {

  constructor(
    private router: Router,
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    x_navigation();
    page_content_onresize();
  }

  protected getUserName(): string {
    return this.authentication.getUserName();
  }

  protected getUserType(): string {
    return this.authentication.userLogged.type;
  }

  logout() {
    console.log('teste');
    this.authentication.logout();
    this.router.navigate(['/']);
  }


}
