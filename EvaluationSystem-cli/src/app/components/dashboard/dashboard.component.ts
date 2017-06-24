import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {Router} from '@angular/router';
import '../../../assets/js/plugins.js';
import '../../../assets/js/actions.js';


declare var $: any;
declare var x_navigation_onresize, x_navigation, page_content_onresize: any;

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
    $(window).resize(function(){
      x_navigation_onresize();
      page_content_onresize();
    });
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
