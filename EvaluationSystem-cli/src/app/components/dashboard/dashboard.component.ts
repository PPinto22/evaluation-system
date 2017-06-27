import {AfterViewInit, Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {BreadCrumbService} from '../../services/breadcrumb.service';
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

  private nameInToggleNavigation;

  constructor(
    private router: Router,
    private authentication: AuthenticationService,
    private breadCrumb: BreadCrumbService
  ) {
  }

  ngOnInit() {
    this.setNamebreadCrum();
  }

  public setNamebreadCrum() {
    this.breadCrumb.breadCrumDate.subscribe( value => {
        this.nameInToggleNavigation = value.pop();
      }
    );
  }

  ngAfterViewInit() {
    x_navigation();
    page_content_onresize();
  }

  public getUserName(): string {
    return this.authentication.getUserName();
  }

  public getUserType(): string {
    return this.authentication.userLogged.type;
  }

  public logout() {
    console.log('teste');
    this.authentication.logout();
    this.router.navigate(['/']);
  }


}
