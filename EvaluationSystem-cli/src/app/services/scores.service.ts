import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {Http} from '@angular/http';
import {HttpUtilService} from './http-util.service';
import {AuthenticationService} from './authentication.service';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ScoresService {

  constructor(private router: Router,
              private http: Http,
              private httpUtil: HttpUtilService,
              private authentication: AuthenticationService) { }

  // GET /api/users/{user_id}/scores
  getUserScore(userId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/users/' + userId + '/scores'),
      this.httpUtil.headers(this.authentication.getToken()) )
        .map( this.httpUtil.extrairDados );
  }
}
