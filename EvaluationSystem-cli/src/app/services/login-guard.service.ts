import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class LoginGuardService {

  constructor(
    private router: Router,
    private authentication: AuthenticationService
  ) { }

  canActivate(): boolean {
    if ( !this.authentication.isLogged() ) {
      return true;
    }
    this.router.navigate(['/dashboard']);
    return false;
  }

}
