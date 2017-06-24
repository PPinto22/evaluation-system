import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(
    private router: Router,
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {

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
