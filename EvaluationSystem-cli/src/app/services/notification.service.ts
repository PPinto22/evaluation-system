import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';
import {Notification} from '../models/notification';

@Injectable()
export class NotificationService {

  constructor( private router: Router,
               private http: Http,
               private httpUtil: HttpUtilService,
               private authentication: AuthenticationService) {
  }

  // GET /api/users/{user_id}/notifications
  getUserNotification(userId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/users/' + userId + '/notifications' ),
            this.httpUtil.headers(this.authentication.getToken()) )
            .map( this.httpUtil.extrairDados );
  }
  // GET /api/invitations/{invitation_id}/accept
  acceptNotification( invitation_id: number ): Observable<any> {
    return this.http.post( this.httpUtil.url('/api/invitations/' + invitation_id + '/accept'), JSON.stringify({}),
      this.httpUtil.headers(this.authentication.getToken()) )
      .map( this.httpUtil.extrairDados );
  }

  // GET /api/invitations/{invitation_id}/decline
  declineNotification( invitation_id: number ): Observable<any> {
    return this.http.post( this.httpUtil.url('/api/invitations/' + invitation_id + '/decline'), JSON.stringify({}),
      this.httpUtil.headers(this.authentication.getToken()) )
      .map( this.httpUtil.extrairDados );
  }

}
