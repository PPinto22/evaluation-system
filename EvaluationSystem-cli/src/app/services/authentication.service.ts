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
               private httpUtil: HttpUtilService) { }


  // LOGIN
  login(email: string, pass: string): Observable<any> {
    return this.http.post( this.httpUtil.url('/auth/login'), JSON.stringify({ email: email, password: pass }), this.httpUtil.headers()).map(
        (response: any) => {
          const data: any = response.json();
          if (data && data.token && data.user) {
            const user: any = data.user;
            localStorage.setItem('currentUser', data.token);
            localStorage.setItem('currentUserId', data.user.id);
            this.userLogged = new User(user.id, user.email, user.firstName, user.lastName, user.type, data.token );
          }
        }
      );
  }

  // REGISTER
  register(email: string, password: string, firstName: string, lastName: string, type: string): Observable<any> {
    return this.http.post( this.httpUtil.url('/auth/signup'),
      JSON.stringify({
        email: email,
        password: password,
        fristName: firstName,
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
    localStorage.removeItem('currentUserId');

  }

  isLogged(): boolean {
    return localStorage['currentUser'] != null ? true : false;
  }

  isTeacher() {
    return this.userLogged.isTeacher();
  }

  isSudent() {
    return this.userLogged.isStudent();
  }

  getUserName(): string {
    return this.userLogged.firstName + ' ' + this.userLogged.lastName;
  }
}
