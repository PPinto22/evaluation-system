import { Injectable } from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';

@Injectable()
export class Exception {



  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) {
  }

  errorHandlingCreateClass(error: any): boolean {
    const p = JSON.parse(error._body);
    if ( p.message === 'Class already exists') {
      return true;
    }
    return false;
  }

  errorHandlingCreateGroup(error: any): boolean {
    const p = JSON.parse(error._body);
    if ( p.message === 'Group already exists') {
      return true;
    }
    return false;
  }

  errorHandlingInvalidToken(error: any): void {
    const error_body = JSON.parse(error._body);
    if ( error.status === 401 && (error_body.message === 'Invalid token' || error_body.message === 'Token expired')) {
      this.authentication.logout();
      this.router.navigate(['/login']);
    }
  }

}
