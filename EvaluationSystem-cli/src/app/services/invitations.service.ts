import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class InvitationsService {

  constructor(private router: Router,
              private http: Http,
              private httpUtil: HttpUtilService,
              private authentication: AuthenticationService) {
  }

  // GET /api/invitations/{invitation_id}/accept
  getInvitationAccept(invitationId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/invitations/' + invitationId + '/accept' ),
            this.httpUtil.headers(this.authentication.getToken()) )
      .map( this.httpUtil.extrairDados );
  }

  // GET /api/invitations/{invitation_id}/decline
  getInvitationDecline(invitationId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/invitations/' + invitationId + '/decline' ),
        this.httpUtil.headers(this.authentication.getToken()) )
      .map( this.httpUtil.extrairDados );
  }
}
