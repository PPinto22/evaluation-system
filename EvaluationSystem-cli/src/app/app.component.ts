import { Component } from '@angular/core';
import {AuthenticationService} from "./services/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(
    private authentication: AuthenticationService
  ) {

  }

  isLogged(): boolean {
    return this.authentication.isLogged();
  }

  logout(): void {
    this.authentication.logout();
  }

  isTeacher(): boolean {
    return localStorage['currentUser'].isTeacher();
  }

  getUserName(): string {
    return this.authentication.getUserName();
  }

}
