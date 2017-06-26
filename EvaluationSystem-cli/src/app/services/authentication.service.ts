import { Injectable } from '@angular/core';
import {User} from '../models/user';
import {Router} from '@angular/router';
import {HttpUtilService} from 'app/services/http-util.service';
import {Http, ResponseType} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {

  userLogged: User; // User with login ok

  constructor( private router: Router,
               private http: Http,
               private httpUtil: HttpUtilService) {
    if ( localStorage['currentUser'] !== undefined) {
      try {
        const userJson = JSON.parse(localStorage['currentUser']);
        this.userLogged = new User(
          userJson._id,
          userJson._email,
          userJson._firstName,
          userJson._lastName,
          userJson._type,
          userJson._token );
      } catch ( error ) {
        localStorage.removeItem('currentUser');
      }

    }
  }


  login(email: string, pass: string): Observable<any> {
    return this.http.post( this.httpUtil.url('/auth/login'), JSON.stringify({ email: email, password: pass }), this.httpUtil.headers()).map(
        (response: any) => {
          const data: any = response.json();
          if (data && data.token && data.user) {
            const user: any = data.user;
            this.userLogged = new User(user.id, user.email, user.firstName, user.lastName, user.type, data.token );
            localStorage.setItem('currentUser', JSON.stringify( this.userLogged ) );
            console.log(JSON.stringify( this.userLogged ));
          }
        }
      );
  }

  register(email: string, password: string, firstName: string, lastName: string, type: string): Observable<any> {
    return this.http.post( this.httpUtil.url('/auth/signup'),
      JSON.stringify({
        email: email,
        password: password,
        firstName: firstName,
        lastName: lastName,
        type: type
      }), this.httpUtil.headers())
      .map(
        (response: any) => {
          const data: any = response.json();
        }
      );
  }

  logout() {
    this.userLogged = null;
    localStorage.removeItem('currentUser');
  }

  isLogged(): boolean {
    return this.userLogged != null ? true : false;
  }

  isTeacher() {
    return this.userLogged.isTeacher();
  }

  getUserId() {
    return this.userLogged.id;
  }

  isSudent() {
    return this.userLogged.isStudent();
  }

  getUserName(): string {
    return this.userLogged.firstName + ' ' + this.userLogged.lastName;
  }
}
