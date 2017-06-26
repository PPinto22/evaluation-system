import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {Router} from '@angular/router';

@Injectable()
export class NotificationService {

  constructor( private router: Router,
               private http: Http,
               private httpUtil: HttpUtilService) {
  }

  // GET /api/users/{user_id}/notifications
  getUserNotification(userId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/users/' + userId + '/notifications' ), this.httpUtil.headers() )
      .map( this.httpUtil.extrairDados );
  }
}
