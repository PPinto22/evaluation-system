import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';

@Injectable()
export class ExamsService {

  constructor(private http: Http,
              private httpUtil: HttpUtilService) { }

  // GET /api/groups/{group_id}/exams
  getExamsByGroupId( groupId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/groups/' + groupId + '/exams'), this.httpUtil.headers() )
      .map( this.httpUtil.extrairDados );
  }

  // GET /api/users/{user_id}/exams
  getExamsByUserId ( userId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/users/' + userId + '/exams'), this.httpUtil.headers() )
      .map( this.httpUtil.extrairDados );
  }

}
