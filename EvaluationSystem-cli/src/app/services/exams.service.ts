import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class ExamsService {

  constructor(private http: Http,
              private httpUtil: HttpUtilService,
              private authentication: AuthenticationService) { }

  // GET /api/groups/{group_id}/exams
  getExamsByGroupId( groupId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/groups/' + groupId + '/exams'),
            this.httpUtil.headers(this.authentication.getToken()) )
            .map( this.httpUtil.extrairDados );
  }

  // GET /api/users/{user_id}/exams
  getExamsByUserId ( userId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/users/' + userId + '/exams'),
            this.httpUtil.headers(this.authentication.getToken()) )
            .map( this.httpUtil.extrairDados );
  }

  // POST /api/groups/{group_id}/exams
  createExamByGroupId ( groupId: number, beginDate: number, duration: number, name: string, questionIds: number[] ): Observable<any> {
    return this.http.post( this.httpUtil.url('/api/groups/' + groupId + '/exams'),
      JSON.stringify({
        beginDate: beginDate,
        duration: duration,
        name: name,
        questionIDs: questionIds
      }), this.httpUtil.headers(this.authentication.getToken()))
      .map(this.httpUtil.extrairDados);
    }

}
