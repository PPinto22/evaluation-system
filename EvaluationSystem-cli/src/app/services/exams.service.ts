import { Injectable } from '@angular/core';
import {Http, RequestOptions, Headers, URLSearchParams, Response} from '@angular/http';
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

  // GET /api/users/{user_id}/exams
  getExamsOnGoingByUserId ( userId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/users/' + userId + '/exams?ongoing'),
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

  // GET /api/exams/{exam_id}
  getExamById( examId: number ): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/exams/' + examId),
      this.httpUtil.headers(this.authentication.getToken()) )
      .map( this.httpUtil.extrairDados );
  }

    // GET /api/users/{user_id}/submissions
  getSubmissionsByExamByGroupId(examId: number, groupId: number ): Observable<any> {
    const headersParams = {
      'Content-Type': 'application/json;charset=UTF-8'
      // 'Content-Type': 'application/x-www-form-urlencoded'
    };
    if (this.authentication.getToken()) {
      headersParams['Authorization'] = 'Bearer ' + this.authentication.getToken();
    }
    var search = new URLSearchParams();
    search.set('exam', '' + examId);
    search.set('group', '' + groupId);
    const headers = new Headers(headersParams);
    const options = new RequestOptions({ headers: headers, search: search});
    return this.http.get( this.httpUtil.url('/api/users/' + this.authentication.getUserId() + '/submissions')
      , options )
      .map( this.httpUtil.extrairDados );
  }

  // POST /api/exams/{exam_id}/submissions
  createExameSubmission( examId: number): Observable<any> {
    return this.http.post( this.httpUtil.url('/api/exams/' + examId + '/submissions'), JSON.stringify({}), this.httpUtil.headers(this.authentication.getToken()))
      .map(this.httpUtil.extrairDados);
  }

  // GET /api/submissions/{submission_id}
  getBySubmission (submissionId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/submissions/' + submissionId),
      this.httpUtil.headers(this.authentication.getToken()) )
      .map( this.httpUtil.extrairDados );
  }

  // PUT /api/submissions/{submission_id}
  putExamAnswerSubmission( submissionId: number, questionId: number, answerId: number): Observable<any> {
    const json: any = {}
    json[questionId] = answerId;
    console.log(json);
    return this.http.put( this.httpUtil.url('/api/submissions/' + submissionId),
        JSON.stringify(json), this.httpUtil.headers(this.authentication.getToken()))
      .map(this.httpUtil.extrairDados);
  }

}
